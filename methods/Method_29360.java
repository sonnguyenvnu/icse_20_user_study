/** 
 * ??????????
 * @param appId
 * @param startDate
 * @param endDate
 * @return
 */
private Map<String,Object> getAppMinuteStat(long appId,Date startDate,Date endDate){
  try {
    String COLLECT_TIME_FORMAT="yyyyMMddHHmm";
    long startTime=NumberUtils.toLong(DateUtil.formatDate(startDate,COLLECT_TIME_FORMAT));
    long endTime=NumberUtils.toLong(DateUtil.formatDate(endDate,COLLECT_TIME_FORMAT));
    return appStatsDao.getAppMinuteStat(appId,startTime,endTime);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyMap();
  }
}
