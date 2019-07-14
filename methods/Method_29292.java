@Override public boolean modifyAppConfig(long appId,String parameter,String value){
  List<InstanceInfo> list=instanceDao.getInstListByAppId(appId);
  if (list == null || list.isEmpty()) {
    logger.error(String.format("appId=%s no instances",appId));
    return false;
  }
  for (  InstanceInfo instance : list) {
    int type=instance.getType();
    if (!TypeUtil.isRedisType(type)) {
      logger.error("appId={};type={};is not redisType",appId,type);
      return false;
    }
    if (TypeUtil.isRedisSentinel(type)) {
      continue;
    }
    if (instance.isOffline()) {
      continue;
    }
    String host=instance.getIp();
    int port=instance.getPort();
    if (!modifyInstanceConfig(appId,host,port,parameter,value)) {
      return false;
    }
  }
  return true;
}
