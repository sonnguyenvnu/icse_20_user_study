@Override public boolean saveStandardStats(Map<String,Object> infoMap,Map<String,Object> clusterInfoMap,String ip,int port,String dbType){
  Assert.isTrue(infoMap != null && infoMap.size() > 0);
  Assert.isTrue(StringUtils.isNotBlank(ip));
  Assert.isTrue(port > 0);
  Assert.isTrue(infoMap.containsKey(ConstUtils.COLLECT_TIME),ConstUtils.COLLECT_TIME + " not in infoMap");
  long collectTime=MapUtils.getLong(infoMap,ConstUtils.COLLECT_TIME);
  StandardStats ss=new StandardStats();
  ss.setCollectTime(collectTime);
  ss.setIp(ip);
  ss.setPort(port);
  ss.setDbType(dbType);
  if (infoMap.containsKey(RedisConstant.DIFF.getValue())) {
    Map<String,Object> diffMap=(Map<String,Object>)infoMap.get(RedisConstant.DIFF.getValue());
    ss.setDiffMap(diffMap);
    infoMap.remove(RedisConstant.DIFF.getValue());
  }
 else {
    ss.setDiffMap(new HashMap<String,Object>(0));
  }
  ss.setInfoMap(infoMap);
  ss.setClusterInfoMap(clusterInfoMap);
  int mergeCount=instanceStatsDao.mergeStandardStats(ss);
  return mergeCount > 0;
}
