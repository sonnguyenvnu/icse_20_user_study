/** 
 * ????????????
 * @param appId
 * @param beginTime
 * @param endTime
 * @return
 */
@Override public List<AppCommandGroup> getAppCommandGroup(long appId,Long beginTime,Long endTime){
  return appStatsDao.getAppCommandGroup(appId,new TimeDimensionality(beginTime,endTime,COLLECT_DATE_FORMAT));
}
