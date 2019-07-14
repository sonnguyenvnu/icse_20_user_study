@Override public boolean shutdownExistInstance(long appId,int instanceId){
  Assert.isTrue(instanceId > 0L);
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
  Assert.isTrue(instanceInfo != null);
  int type=instanceInfo.getType();
  String host=instanceInfo.getIp();
  int port=instanceInfo.getPort();
  boolean isShutdown;
  if (TypeUtil.isRedisType(type)) {
    if (TypeUtil.isRedisSentinel(type)) {
      isShutdown=redisCenter.shutdown(host,port);
    }
 else {
      isShutdown=redisCenter.shutdown(appId,host,port);
    }
    if (isShutdown) {
      logger.warn("{}:{} redis is shutdown",host,port);
    }
 else {
      logger.error("{}:{} redis shutdown error",host,port);
    }
  }
 else {
    logger.error("type={} not match!",type);
    isShutdown=false;
  }
  if (isShutdown) {
    instanceInfo.setStatus(InstanceStatusEnum.OFFLINE_STATUS.getStatus());
    instanceDao.update(instanceInfo);
    if (TypeUtil.isRedisType(type)) {
      redisCenter.unDeployRedisCollection(instanceInfo.getAppId(),instanceInfo.getIp(),instanceInfo.getPort());
      redisCenter.unDeployRedisSlowLogCollection(instanceInfo.getAppId(),host,port);
    }
  }
  return isShutdown;
}
