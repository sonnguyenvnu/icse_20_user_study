@Override public boolean cleanAppData(AppDesc appDesc,AppUser appUser){
  if (appDesc == null) {
    return false;
  }
  long appId=appDesc.getAppId();
  if (AppDescEnum.AppTest.IS_TEST.getValue() != appDesc.getIsTest()) {
    logger.error("appId {} profile must be test",appId);
    return false;
  }
  if (!TypeUtil.isRedisType(appDesc.getType())) {
    logger.error("appId {} type must be redis",appId);
    return false;
  }
  List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
  if (CollectionUtils.isEmpty(instanceList)) {
    logger.error("appId {} instanceList is empty",appId);
    return false;
  }
  for (  InstanceInfo instance : instanceList) {
    if (instance.getStatus() != InstanceStatusEnum.GOOD_STATUS.getStatus()) {
      continue;
    }
    String host=instance.getIp();
    int port=instance.getPort();
    Boolean isMater=isMaster(appId,host,port);
    if (isMater != null && isMater.equals(true) && !TypeUtil.isRedisSentinel(instance.getType())) {
      Jedis jedis=getJedis(appId,host,port);
      jedis.getClient().setConnectionTimeout(REDIS_DEFAULT_TIME);
      jedis.getClient().setSoTimeout(60000);
      try {
        logger.warn("{}:{} start clear data",host,port);
        long start=System.currentTimeMillis();
        String result=jedis.flushAll();
        if (!"ok".equalsIgnoreCase(result)) {
          return false;
        }
        logger.warn("{}:{} finish clear data, cost time:{} ms",host,port,(System.currentTimeMillis() - start));
      }
 catch (      Exception e) {
        logger.error("clear redis: " + e.getMessage(),e);
        return false;
      }
 finally {
        jedis.close();
      }
    }
  }
  AppAuditLog appAuditLog=AppAuditLog.generate(appDesc,appUser,0L,AppAuditLogTypeEnum.APP_CLEAN_DATA);
  appAuditLogDao.save(appAuditLog);
  return true;
}
