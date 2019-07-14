/** 
 * ????????????????????
 * @param appId       ??id
 * @param beginTime   ??????yyyyMMddHHmm
 * @param endTime     ??????yyyyMMddHHmm
 * @param commandName ???
 * @return
 */
@Override public List<AppCommandStats> getCommandStatsList(long appId,long beginTime,long endTime,String commandName){
  return appStatsDao.getAppCommandStatsList(appId,commandName,new TimeDimensionality(beginTime,endTime,COLLECT_DATE_FORMAT));
}
