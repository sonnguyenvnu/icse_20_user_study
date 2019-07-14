@Override public List<AppCommandStats> getCommandStatsListV2(long appId,long beginTime,long endTime,TimeDimensionalityEnum timeDimensionalityEnum){
  if (TimeDimensionalityEnum.MINUTE.equals(timeDimensionalityEnum)) {
    return appStatsDao.getAppAllCommandStatsListByMinute(appId,beginTime,endTime);
  }
 else   if (TimeDimensionalityEnum.HOUR.equals(timeDimensionalityEnum)) {
    return appStatsDao.getAppAllCommandStatsListByHour(appId,beginTime,endTime);
  }
  return Collections.emptyList();
}
