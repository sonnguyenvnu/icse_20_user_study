@Override public MetaServerConsoleService.PreviousPrimaryDcMessage deprepare(String clusterId,String shardId){
  logger.info("[deprepare]{}, {}",clusterId,shardId);
  MetaServerConsoleService.PreviousPrimaryDcMessage message=new MetaServerConsoleService.PreviousPrimaryDcMessage();
  ExecutionLog executionLog=new ExecutionLog(String.format("meta server:%s",currentClusterServer.getClusterInfo()));
  Pair<String,Integer> keeperMaster=currentMetaManager.getKeeperMaster(clusterId,shardId);
  message.setMasterAddr(new HostPort(keeperMaster.getKey(),keeperMaster.getValue()));
  executionLog.info("[deprepare]" + keeperMaster);
  makeMasterReadOnly(clusterId,shardId,keeperMaster,false,executionLog);
  addSentinel(clusterId,shardId,executionLog);
  message.setMessage(executionLog.getLog());
  return message;
}
