@Override public List<AppCommandStats> getCommandStatsListV2(long appId,long beginTime,long endTime,TimeDimensionalityEnum timeDimensionalityEnum,String commandName){
  if (TimeDimensionalityEnum.MINUTE.equals(timeDimensionalityEnum)) {
    return appStatsDao.getAppCommandStatsListByMinuteWithCommand(appId,beginTime,endTime,commandName);
  }
 else   if (TimeDimensionalityEnum.HOUR.equals(timeDimensionalityEnum)) {
    return appStatsDao.getAppCommandStatsListByHourWithCommand(appId,beginTime,endTime,commandName);
  }
  return Collections.emptyList();
}
