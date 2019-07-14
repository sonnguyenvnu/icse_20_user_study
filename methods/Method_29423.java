@Override public Map<String,Object> queryStandardInfoMap(long collectTime,String ip,int port,String dbType){
  Assert.isTrue(StringUtils.isNotBlank(ip));
  Assert.isTrue(port > 0);
  Assert.isTrue(collectTime > 0);
  StandardStats ss=instanceStatsDao.getStandardStats(collectTime,ip,port,dbType);
  if (ss != null) {
    Map<String,Object> infoMap=ss.getInfoMap();
    Map<String,Object> diffMap=ss.getDiffMap();
    infoMap.put(RedisConstant.DIFF.getValue(),diffMap);
    return infoMap;
  }
 else {
    return Collections.emptyMap();
  }
}
