@Override public Map<RedisConstant,Map<String,Object>> collectRedisInfo(long appId,long collectTime,String host,int port){
  Assert.isTrue(appId > 0);
  Assert.hasText(host);
  Assert.isTrue(port > 0);
  long start=System.currentTimeMillis();
  InstanceInfo instanceInfo=instanceDao.getInstByIpAndPort(host,port);
  if (instanceInfo == null) {
    return null;
  }
  if (TypeUtil.isRedisSentinel(instanceInfo.getType())) {
    return null;
  }
  Map<RedisConstant,Map<String,Object>> infoMap=this.getInfoStats(appId,host,port);
  if (infoMap == null || infoMap.isEmpty()) {
    logger.error("appId:{},collectTime:{},host:{},port:{} cost={} ms redis infoMap is null",new Object[]{appId,collectTime,host,port,(System.currentTimeMillis() - start)});
    return infoMap;
  }
  Map<String,Object> clusterInfoMap=getClusterInfoStats(appId,instanceInfo);
  boolean isOk=asyncService.submitFuture(new RedisKeyCallable(appId,collectTime,host,port,infoMap,clusterInfoMap));
  if (!isOk) {
    logger.error("submitFuture failed,appId:{},collectTime:{},host:{},port:{} cost={} ms",new Object[]{appId,collectTime,host,port,(System.currentTimeMillis() - start)});
  }
  return infoMap;
}
