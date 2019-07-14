@Override public List<RedisSlowLog> getRedisSlowLogs(int instanceId,int maxCount){
  if (instanceId <= 0) {
    return Collections.emptyList();
  }
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
  if (instanceInfo == null) {
    return Collections.emptyList();
  }
  if (TypeUtil.isRedisType(instanceInfo.getType())) {
    return getRedisSlowLogs(instanceInfo.getAppId(),instanceInfo.getIp(),instanceInfo.getPort(),maxCount);
  }
  return Collections.emptyList();
}
