@Override public boolean deployStandaloneInstance(long appId,String host,int maxMemory){
  if (!isExist(appId)) {
    return false;
  }
  AppDesc appDesc=appDao.getAppDescById(appId);
  Integer port=machineCenter.getAvailablePort(host,ConstUtils.CACHE_REDIS_STANDALONE);
  if (port == null) {
    logger.error("masterHost={} getAvailablePort is null",host);
    return false;
  }
  boolean isMasterRun=runInstance(appDesc,host,port,maxMemory,false);
  if (!isMasterRun) {
    return false;
  }
  saveInstance(appId,host,port,maxMemory,ConstUtils.CACHE_REDIS_STANDALONE,"");
  boolean isMasterDeploy=redisCenter.deployRedisCollection(appId,host,port);
  if (!isMasterDeploy) {
    logger.warn("host={},port={},isMasterDeploy=false",host,port);
  }
  return true;
}
