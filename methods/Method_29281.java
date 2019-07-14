@Override public boolean deploySentinelInstance(long appId,String masterHost,String slaveHost,int maxMemory,List<String> sentinelList){
  if (!isExist(appId)) {
    return false;
  }
  AppDesc appDesc=appDao.getAppDescById(appId);
  Integer masterPort=machineCenter.getAvailablePort(masterHost,ConstUtils.CACHE_REDIS_STANDALONE);
  if (masterPort == null) {
    logger.error("masterHost={} getAvailablePort is null",masterHost);
    return false;
  }
  Integer slavePort=machineCenter.getAvailablePort(slaveHost,ConstUtils.CACHE_REDIS_STANDALONE);
  if (slavePort == null) {
    logger.error("slaveHost={} getAvailablePort is null",slavePort);
    return false;
  }
  boolean isMasterRun=runInstance(appDesc,masterHost,masterPort,maxMemory,false);
  if (!isMasterRun) {
    return false;
  }
  boolean isSlaveRun=runInstance(appDesc,slaveHost,slavePort,maxMemory,false);
  if (!isSlaveRun) {
    return false;
  }
  boolean isSlave=slaveOf(appDesc.getAppId(),masterHost,masterPort,slaveHost,slavePort);
  if (!isSlave) {
    return false;
  }
  boolean isRunSentinel=runSentinelGroup(appDesc,sentinelList,masterHost,masterPort,appId,appDesc.getPassword());
  if (!isRunSentinel) {
    return false;
  }
  saveInstance(appId,masterHost,masterPort,maxMemory,ConstUtils.CACHE_REDIS_STANDALONE,"");
  saveInstance(appId,slaveHost,slavePort,maxMemory,ConstUtils.CACHE_REDIS_STANDALONE,"");
  boolean isMasterDeploy=redisCenter.deployRedisCollection(appId,masterHost,masterPort);
  boolean isSlaveDeploy=redisCenter.deployRedisCollection(appId,slaveHost,slavePort);
  if (!isMasterDeploy) {
    logger.warn("host={},port={},isMasterDeploy=false",masterHost,masterPort);
  }
  if (!isSlaveDeploy) {
    logger.warn("host={},port={},isSlaveDeploy=false",slaveHost,slavePort);
  }
  return true;
}
