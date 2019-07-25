@Override public void sync(ResyncReplicationRequest request,Task parentTask,String primaryAllocationId,long primaryTerm,ActionListener<ResyncReplicationResponse> listener){
  transportService.sendChildRequest(clusterService.localNode(),transportPrimaryAction,new ConcreteShardRequest<>(request,primaryAllocationId,primaryTerm),parentTask,transportOptions,new TransportResponseHandler<ResyncReplicationResponse>(){
    @Override public ResyncReplicationResponse newInstance(){
      return newResponseInstance();
    }
    @Override public String executor(){
      return ThreadPool.Names.SAME;
    }
    @Override public void handleResponse(    ResyncReplicationResponse response){
      final ReplicationResponse.ShardInfo.Failure[] failures=response.getShardInfo().getFailures();
      for (int i=0; i < failures.length; i++) {
        final ReplicationResponse.ShardInfo.Failure f=failures[i];
        logger.info(new ParameterizedMessage("{} primary-replica resync to replica on node [{}] failed",f.fullShardId(),f.nodeId()),f.getCause());
      }
      listener.onResponse(response);
    }
    @Override public void handleException(    TransportException exp){
      final Throwable cause=exp.unwrapCause();
      if (TransportActions.isShardNotAvailableException(cause)) {
        logger.trace("primary became unavailable during resync, ignoring",exp);
      }
 else {
        listener.onFailure(exp);
      }
    }
  }
);
}
