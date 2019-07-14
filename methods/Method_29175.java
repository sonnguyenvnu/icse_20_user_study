/** 
 * ???????
 */
private int getSuitableDimensionality(Date begin,Date end){
  try {
    long s1=begin.getTime();
    long s2=end.getTime();
    long hour=(s2 - s1) / 1000 / 60 / 60;
    if (hour >= 48) {
      return AppStatsDao.HOUR_DIMENSIONALITY;
    }
 else {
      return AppStatsDao.MINUTE_DIMENSIONALITY;
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return AppStatsDao.MINUTE_DIMENSIONALITY;
}
