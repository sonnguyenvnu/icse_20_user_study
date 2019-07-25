@Override public MetaServerConsoleService.PreviousPrimaryDcMessage prepare(String clusterId,String shardId){
  logger.info("[prepare]{}, {}",clusterId,shardId);
  MetaServerConsoleService.PreviousPrimaryDcMessage message=new MetaServerConsoleService.PreviousPrimaryDcMessage();
  ExecutionLog executionLog=new ExecutionLog(String.format("meta server:%s",currentClusterServer.getClusterInfo()));
  Pair<String,Integer> keeperMaster=currentMetaManager.getKeeperMaster(clusterId,shardId);
  message.setMasterAddr(new HostPort(keeperMaster.getKey(),keeperMaster.getValue()));
  executionLog.info("[prepare]" + keeperMaster);
  RedisInfo redisInfo=getInfoReplication(keeperMaster,executionLog);
  MasterInfo masterInfo=convert(redisInfo,executionLog);
  message.setMasterInfo(masterInfo);
  logger.info("[prepare]{}, {}, {}",keeperMaster,redisInfo,masterInfo);
  makeMasterReadOnly(clusterId,shardId,keeperMaster,true,executionLog);
  removeSentinel(clusterId,shardId,executionLog);
  message.setMessage(executionLog.getLog());
  return message;
}
