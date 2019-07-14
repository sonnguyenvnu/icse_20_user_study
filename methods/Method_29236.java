/** 
 * ???????
 * @param currentInfoMap
 * @return ????map
 */
private Table<RedisConstant,String,Long> getAccumulationDiff(Map<RedisConstant,Map<String,Object>> currentInfoMap,Map<String,Object> lastInfoMap){
  if (lastInfoMap == null || lastInfoMap.isEmpty()) {
    return HashBasedTable.create();
  }
  Map<RedisInfoEnum,Long> currentMap=new LinkedHashMap<RedisInfoEnum,Long>();
  for (  RedisInfoEnum acc : RedisInfoEnum.getNeedCalDifRedisInfoEnumList()) {
    Long count=getCommonCount(currentInfoMap,acc.getRedisConstant(),acc.getValue());
    if (count != null) {
      currentMap.put(acc,count);
    }
  }
  Map<RedisInfoEnum,Long> lastMap=new LinkedHashMap<RedisInfoEnum,Long>();
  for (  RedisInfoEnum acc : RedisInfoEnum.getNeedCalDifRedisInfoEnumList()) {
    if (lastInfoMap != null) {
      Long lastCount=getCommonCount(lastInfoMap,acc.getRedisConstant(),acc.getValue());
      if (lastCount != null) {
        lastMap.put(acc,lastCount);
      }
    }
  }
  Table<RedisConstant,String,Long> resultTable=HashBasedTable.create();
  for (  RedisInfoEnum key : currentMap.keySet()) {
    Long value=MapUtils.getLong(currentMap,key,null);
    Long lastValue=MapUtils.getLong(lastMap,key,null);
    if (value == null || lastValue == null) {
      continue;
    }
    long diff=0L;
    if (value > lastValue) {
      diff=value - lastValue;
    }
    resultTable.put(key.getRedisConstant(),key.getValue(),diff);
  }
  return resultTable;
}
