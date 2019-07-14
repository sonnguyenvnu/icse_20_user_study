@Override public List<InstanceSlowLog> getInstanceSlowLogByAppId(long appId,Date startDate,Date endDate){
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (appDesc == null) {
    return Collections.emptyList();
  }
  if (TypeUtil.isRedisType(appDesc.getType())) {
    return redisCenter.getInstanceSlowLogByAppId(appId,startDate,endDate);
  }
  return Collections.emptyList();
}
