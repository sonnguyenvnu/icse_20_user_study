/** 
 * ????????????????????
 * @param appId     ??id
 * @param beginTime ??????yyyyMMddHHmm
 * @param endTime   ??????yyyyMMddHHmm
 * @return
 */
@Override public List<AppCommandStats> getCommandStatsList(long appId,long beginTime,long endTime){
  return appStatsDao.getAppAllCommandStatsList(appId,new TimeDimensionality(beginTime,endTime,COLLECT_DATE_FORMAT));
}
