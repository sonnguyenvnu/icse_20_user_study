/** 
 * ??????
 * @param appId
 * @param startDate
 * @param endDate
 * @return
 */
private int getClientExceptionCount(long appId,Date startDate,Date endDate){
  try {
    String COLLECT_TIME_FORMAT="yyyyMMddHHmmss";
    long startTime=NumberUtils.toLong(DateUtil.formatDate(startDate,COLLECT_TIME_FORMAT));
    long endTime=NumberUtils.toLong(DateUtil.formatDate(endDate,COLLECT_TIME_FORMAT));
    return appClientExceptionStatDao.getAppExceptionCount(appId,startTime,endTime,-1,null);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return STAT_ERROR;
  }
}
