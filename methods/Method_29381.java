@Override public boolean offLineApp(Long appId){
  Assert.isTrue(appId != null && appId > 0L);
  AppDesc appDesc=appService.getByAppId(appId);
  if (appDesc == null) {
    logger.error("appId={} not exist",appId);
    return false;
  }
  List<InstanceInfo> instanceInfos=instanceDao.getInstListByAppId(appId);
  int type=appDesc.getType();
  if (instanceInfos != null) {
    for (    InstanceInfo instanceInfo : instanceInfos) {
      final String ip=instanceInfo.getIp();
      final int port=instanceInfo.getPort();
      if (TypeUtil.isRedisType(type)) {
        redisCenter.unDeployRedisCollection(appId,ip,port);
        redisCenter.unDeployRedisSlowLogCollection(appId,ip,port);
        boolean isShutdown=redisCenter.shutdown(appId,ip,port);
        if (!isShutdown) {
          logger.error("{}:{} redis not shutdown!",ip,port);
          return false;
        }
      }
      instanceInfo.setStatus(InstanceStatusEnum.OFFLINE_STATUS.getStatus());
      instanceDao.update(instanceInfo);
    }
  }
  appDesc.setStatus(AppStatusEnum.STATUS_OFFLINE.getStatus());
  appService.update(appDesc);
  return true;
}
