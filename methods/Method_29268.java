@Override public String getRedisVersion(long appId,String ip,int port){
  Map<RedisConstant,Map<String,Object>> infoAllMap=getInfoStats(appId,ip,port);
  if (MapUtils.isEmpty(infoAllMap)) {
    return null;
  }
  Map<String,Object> serverMap=infoAllMap.get(RedisConstant.Server);
  if (MapUtils.isEmpty(serverMap)) {
    return null;
  }
  return MapUtils.getString(serverMap,"redis_version");
}
