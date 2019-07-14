@Override public boolean verticalExpansion(Long appId,Long appAuditId,final int memory){
  Assert.isTrue(appId != null && appId > 0L);
  Assert.isTrue(appAuditId != null && appAuditId > 0L);
  Assert.isTrue(memory > 0);
  AppDesc appDesc=appService.getByAppId(appId);
  Assert.isTrue(appDesc != null);
  int type=appDesc.getType();
  if (!TypeUtil.isRedisType(type)) {
    logger.error("appId={};type={} is not redis!",appDesc,type);
    return false;
  }
  List<InstanceInfo> instanceInfos=instanceDao.getInstListByAppId(appId);
  if (instanceInfos == null || instanceInfos.isEmpty()) {
    logger.error("instanceInfos is null");
    return false;
  }
  for (  InstanceInfo instanceInfo : instanceInfos) {
    int instanceType=instanceInfo.getType();
    if (TypeUtil.isRedisSentinel(instanceType)) {
      continue;
    }
    if (instanceInfo.isOffline()) {
      continue;
    }
    String host=instanceInfo.getIp();
    int port=instanceInfo.getPort();
    final long maxMemoryBytes=Long.valueOf(memory) * 1024 * 1024;
    boolean isConfig=redisDeployCenter.modifyInstanceConfig(appId,host,port,"maxmemory",String.valueOf(maxMemoryBytes));
    if (!isConfig) {
      logger.error("{}:{} set maxMemory error",host,port);
      return false;
    }
    instanceInfo.setMem(memory);
    instanceDao.update(instanceInfo);
  }
  appAuditDao.updateAppAudit(appAuditId,AppCheckEnum.APP_ALLOCATE_RESOURCE.value());
  return true;
}
