/** 
 * ????????app???????
 * @param appId
 * @param beginTime ??????yyyyMMddHHmm
 * @param endTime   ??????yyyyMMddHHmm
 * @return
 */
@Override public List<AppStats> getAppStatsList(final long appId,long beginTime,long endTime,TimeDimensionalityEnum timeDimensionalityEnum){
  Assert.isTrue(appId > 0);
  Assert.isTrue(beginTime > 0 && endTime > 0);
  List<AppStats> appStatsList=null;
  try {
    if (TimeDimensionalityEnum.MINUTE.equals(timeDimensionalityEnum)) {
      appStatsList=appStatsDao.getAppStatsByMinute(appId,beginTime,endTime);
    }
 else     if (TimeDimensionalityEnum.HOUR.equals(timeDimensionalityEnum)) {
      appStatsList=appStatsDao.getAppStatsByHour(appId,beginTime,endTime);
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return appStatsList;
}
