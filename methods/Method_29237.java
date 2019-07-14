private long getObjectSize(Map<RedisConstant,Map<String,Object>> currentInfoMap){
  Map<String,Object> sizeMap=currentInfoMap.get(RedisConstant.Keyspace);
  if (sizeMap == null || sizeMap.isEmpty()) {
    return 0L;
  }
  long result=0L;
  Map<String,Long> longSizeMap=transferLongMap(sizeMap);
  for (  String key : longSizeMap.keySet()) {
    result+=longSizeMap.get(key);
  }
  return result;
}
