private void run(BiFunction<String,String,DiscoveryNode> clusterNodeLookup,final ScrollIdForNode[] context){
  final CountDown counter=new CountDown(scrollId.getContext().length);
  for (int i=0; i < context.length; i++) {
    ScrollIdForNode target=context[i];
    final int shardIndex=i;
    final Transport.Connection connection;
    try {
      DiscoveryNode node=clusterNodeLookup.apply(target.getClusterAlias(),target.getNode());
      if (node == null) {
        throw new IllegalStateException("node [" + target.getNode() + "] is not available");
      }
      connection=getConnection(target.getClusterAlias(),node);
    }
 catch (    Exception ex) {
      onShardFailure("query",counter,target.getScrollId(),ex,null,() -> SearchScrollAsyncAction.this.moveToNextPhase(clusterNodeLookup));
      continue;
    }
    final InternalScrollSearchRequest internalRequest=internalScrollSearchRequest(target.getScrollId(),request);
    SearchActionListener<T> searchActionListener=new SearchActionListener<T>(null,shardIndex){
      @Override protected void setSearchShardTarget(      T response){
        assert response.getSearchShardTarget() != null : "search shard target must not be null";
        if (target.getClusterAlias() != null) {
          SearchShardTarget searchShardTarget=response.getSearchShardTarget();
          response.setSearchShardTarget(new SearchShardTarget(searchShardTarget.getNodeId(),searchShardTarget.getShardId(),target.getClusterAlias(),null));
        }
      }
      @Override protected void innerOnResponse(      T result){
        assert shardIndex == result.getShardIndex() : "shard index mismatch: " + shardIndex + " but got: " + result.getShardIndex();
        onFirstPhaseResult(shardIndex,result);
        if (counter.countDown()) {
          SearchPhase phase=moveToNextPhase(clusterNodeLookup);
          try {
            phase.run();
          }
 catch (          Exception e) {
            listener.onFailure(new SearchPhaseExecutionException(phase.getName(),"Phase failed",e,ShardSearchFailure.EMPTY_ARRAY));
          }
        }
      }
      @Override public void onFailure(      Exception t){
        onShardFailure("query",counter,target.getScrollId(),t,null,() -> SearchScrollAsyncAction.this.moveToNextPhase(clusterNodeLookup));
      }
    }
;
    executeInitialPhase(connection,internalRequest,searchActionListener);
  }
}
