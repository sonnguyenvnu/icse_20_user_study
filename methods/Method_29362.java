/** 
 * ???????????????
 * @param appId
 * @param startDate
 * @param endDate
 * @return
 */
private int getSlowLogCount(long appId,Date startDate,Date endDate){
  try {
    return instanceSlowLogDao.getAppSlowLogCount(appId,startDate,endDate);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return STAT_ERROR;
  }
}
