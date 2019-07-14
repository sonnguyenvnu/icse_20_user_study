@Override public boolean sentinelFailover(final long appId) throws Exception {
  Assert.isTrue(appId > 0);
  AppDesc appDesc=appDao.getAppDescById(appId);
  Assert.isTrue(appDesc != null);
  int type=appDesc.getType();
  if (!TypeUtil.isRedisSentinel(type)) {
    logger.warn("app={} is not sentinel",appDesc);
    return false;
  }
  final List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
  if (instanceList == null || instanceList.isEmpty()) {
    logger.warn("app={} instances is empty");
    return false;
  }
  for (  InstanceInfo instanceInfo : instanceList) {
    int instanceType=instanceInfo.getType();
    if (TypeUtil.isRedisSentinel(instanceType)) {
      final String host=instanceInfo.getIp();
      final int port=instanceInfo.getPort();
      final String masterName=instanceInfo.getCmd();
      if (StringUtils.isBlank(masterName)) {
        logger.warn("{} cmd is null",instanceInfo);
        continue;
      }
      boolean isRun=redisCenter.isRun(host,port);
      if (!isRun) {
        logger.warn("{} is not run");
        continue;
      }
      boolean isSentinelFailOver=new IdempotentConfirmer(){
        @Override public boolean execute(){
          Jedis jedis=redisCenter.getJedis(host,port);
          try {
            String response=jedis.sentinelFailover(masterName);
            return response != null && response.equalsIgnoreCase("OK");
          }
  finally {
            jedis.close();
          }
        }
      }
.run();
      if (!isSentinelFailOver) {
        logger.warn("{}:{} sentienl isSentinelFailOver error",host,port);
        return false;
      }
 else {
        logger.warn("SentinelFailOver done! ");
        break;
      }
    }
  }
  return true;
}
