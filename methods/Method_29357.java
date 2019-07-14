public AppDailyData generateAppDaily(long appId,Date startDate,Date endDate){
  Assert.isTrue(appId > 0L);
  AppDetailVO appDetailVO=appStatsCenter.getAppDetail(appId);
  if (appDetailVO == null) {
    logger.error("appId={} not exist",appId);
    return null;
  }
  AppDesc appDesc=appDetailVO.getAppDesc();
  if (appDesc.isOffline()) {
    return null;
  }
  if (appDesc.isTest()) {
    return null;
  }
  AppDailyData appDailyData=new AppDailyData();
  appDailyData.setStartDate(startDate);
  appDailyData.setEndDate(endDate);
  appDailyData.setAppDetailVO(appDetailVO);
  int slowLogCount=getSlowLogCount(appId,startDate,endDate);
  appDailyData.setSlowLogCount(slowLogCount);
  int clientExceptionCount=getClientExceptionCount(appId,startDate,endDate);
  appDailyData.setClientExceptionCount(clientExceptionCount);
  Map<String,Long> valueSizeDistributeCountMap=getAppClientValueSizeDistributeCountMap(appId,startDate,endDate);
  appDailyData.setValueSizeDistributeCountMap(valueSizeDistributeCountMap);
  Map<String,Object> appMinuteStatMap=getAppMinuteStat(appId,startDate,endDate);
  appDailyData.setMaxMinuteClientCount(MapUtils.getIntValue(appMinuteStatMap,"maxClientCount"));
  appDailyData.setAvgMinuteClientCount(MapUtils.getIntValue(appMinuteStatMap,"avgClientCount"));
  appDailyData.setMaxMinuteCommandCount(MapUtils.getIntValue(appMinuteStatMap,"maxCommandCount"));
  appDailyData.setAvgMinuteCommandCount(MapUtils.getIntValue(appMinuteStatMap,"avgCommandCount"));
  appDailyData.setMaxMinuteHitRatio(remainNumberTwoPoint(MapUtils.getDoubleValue(appMinuteStatMap,"maxHitRatio") * 100.0));
  appDailyData.setMinMinuteHitRatio(remainNumberTwoPoint(MapUtils.getDoubleValue(appMinuteStatMap,"minHitRatio") * 100.0));
  appDailyData.setAvgHitRatio(remainNumberTwoPoint(MapUtils.getDoubleValue(appMinuteStatMap,"avgHitRatio") * 100.0));
  appDailyData.setAvgUsedMemory(MapUtils.getLongValue(appMinuteStatMap,"avgUsedMemory") / 1024 / 1024);
  appDailyData.setMaxUsedMemory(MapUtils.getLongValue(appMinuteStatMap,"maxUsedMemory") / 1024 / 1024);
  appDailyData.setExpiredKeysCount(MapUtils.getIntValue(appMinuteStatMap,"expiredKeys"));
  appDailyData.setEvictedKeysCount(MapUtils.getIntValue(appMinuteStatMap,"evictedKeys"));
  appDailyData.setAvgMinuteNetOutputByte(remainNumberTwoPoint(MapUtils.getDoubleValue(appMinuteStatMap,"avgNetInputByte") / 1024.0 / 1024.0));
  appDailyData.setMaxMinuteNetOutputByte(remainNumberTwoPoint(MapUtils.getDoubleValue(appMinuteStatMap,"maxNetInputByte") / 1024.0 / 1024.0));
  appDailyData.setAvgMinuteNetInputByte(remainNumberTwoPoint(MapUtils.getDoubleValue(appMinuteStatMap,"avgNetOutputByte") / 1024.0 / 1024.0));
  appDailyData.setMaxMinuteNetInputByte(remainNumberTwoPoint(MapUtils.getDoubleValue(appMinuteStatMap,"maxNetOutputByte") / 1024.0 / 1024.0));
  appDailyData.setAvgObjectSize(MapUtils.getIntValue(appMinuteStatMap,"avgObjectSize"));
  appDailyData.setMaxObjectSize(MapUtils.getIntValue(appMinuteStatMap,"maxObjectSize"));
  return appDailyData;
}
