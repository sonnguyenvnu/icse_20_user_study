@Override public boolean addSentinel(long appId,String sentinelHost){
  AppDesc appDesc=appDao.getAppDescById(appId);
  JedisSentinelPool jedisSentinelPool=redisCenter.getJedisSentinelPool(appDesc);
  if (jedisSentinelPool == null) {
    return false;
  }
  List<InstanceInfo> instanceInfos=instanceDao.getInstListByAppId(appId);
  String masterName=null;
  for (Iterator<InstanceInfo> i=instanceInfos.iterator(); i.hasNext(); ) {
    InstanceInfo instanceInfo=i.next();
    if (instanceInfo.getType() != ConstUtils.CACHE_REDIS_SENTINEL) {
      i.remove();
      continue;
    }
    if (masterName == null && StringUtils.isNotBlank(instanceInfo.getCmd())) {
      masterName=instanceInfo.getCmd();
    }
  }
  Jedis jedis=null;
  String masterHost=null;
  Integer masterPort=null;
  try {
    jedis=jedisSentinelPool.getResource();
    masterHost=jedis.getClient().getHost();
    masterPort=jedis.getClient().getPort();
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    jedis.close();
    jedisSentinelPool.destroy();
  }
  boolean isRun=runSentinel(appDesc,sentinelHost,masterName,masterHost,masterPort);
  if (!isRun) {
    return false;
  }
  return true;
}
