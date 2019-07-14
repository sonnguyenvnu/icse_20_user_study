/** 
 * ??infoMap??????????
 * @param infoMap
 * @return
 */
private Boolean hasSlaves(Map<RedisConstant,Map<String,Object>> infoMap){
  Map<String,Object> replicationMap=infoMap.get(RedisConstant.Replication);
  if (MapUtils.isEmpty(replicationMap)) {
    return null;
  }
  for (  Entry<String,Object> entry : replicationMap.entrySet()) {
    String key=entry.getKey();
    if (key != null && key.contains("slave0")) {
      return true;
    }
  }
  return false;
}
