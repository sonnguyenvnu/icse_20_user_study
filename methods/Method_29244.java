/** 
 * ??infoMap??????????
 * @param infoMap
 * @return
 */
private Boolean isMaster(Map<RedisConstant,Map<String,Object>> infoMap){
  Map<String,Object> map=infoMap.get(RedisConstant.Replication);
  if (map == null || map.get(RedisInfoEnum.role.getValue()) == null) {
    return null;
  }
  if (String.valueOf(map.get(RedisInfoEnum.role.getValue())).equals("master")) {
    return true;
  }
  return false;
}
