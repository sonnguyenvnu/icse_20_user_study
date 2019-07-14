@Override public List<Map<String,Object>> queryDiffMapList(long beginTime,long endTime,String ip,int port,String dbType){
  Assert.isTrue(StringUtils.isNotBlank(ip));
  Assert.isTrue(port > 0);
  Assert.isTrue(beginTime > 0);
  Assert.isTrue(endTime > 0);
  List<StandardStats> list=instanceStatsDao.getDiffJsonList(beginTime,endTime,ip,port,dbType);
  if (list == null || list.isEmpty()) {
    return new ArrayList<Map<String,Object>>(0);
  }
  List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>(list.size());
  for (  StandardStats ss : list) {
    Map<String,Object> diffMap=ss.getDiffMap();
    diffMap.put(ConstUtils.COLLECT_TIME,ss.getCollectTime());
    resultList.add(diffMap);
  }
  return resultList;
}
