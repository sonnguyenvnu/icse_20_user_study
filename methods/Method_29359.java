private Map<String,Long> getAppClientValueSizeDistributeCountMap(long appId,Date startDate,Date endDate){
  try {
    String COLLECT_TIME_FORMAT="yyyyMMddHHmmss";
    long startTime=NumberUtils.toLong(DateUtil.formatDate(startDate,COLLECT_TIME_FORMAT));
    long endTime=NumberUtils.toLong(DateUtil.formatDate(endDate,COLLECT_TIME_FORMAT));
    List<AppClientValueDistriSimple> appClientValueDistriSimpleList=appClientValueStatDao.getAppValueDistriList(appId,startTime,endTime);
    Map<String,Long> valueSizeInfoCountMap=new TreeMap<String,Long>();
    for (    AppClientValueDistriSimple appClientValueDistriSimple : appClientValueDistriSimpleList) {
      valueSizeInfoCountMap.put(appClientValueDistriSimple.getDistributeDesc(),appClientValueDistriSimple.getCount());
    }
    return valueSizeInfoCountMap;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return Collections.emptyMap();
  }
}
