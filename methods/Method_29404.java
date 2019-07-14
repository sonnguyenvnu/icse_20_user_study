@Override public Map<String,Long> getInstanceSlowLogCountMapByAppId(Long appId,Date startDate,Date endDate){
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (appDesc == null) {
    return Collections.emptyMap();
  }
  if (TypeUtil.isRedisType(appDesc.getType())) {
    return redisCenter.getInstanceSlowLogCountMapByAppId(appId,startDate,endDate);
  }
  return Collections.emptyMap();
}
