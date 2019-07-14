/** 
 * ??redis????
 * @param statResult ?????
 */
private Map<RedisConstant,Map<String,Object>> processRedisStats(String statResult){
  Map<RedisConstant,Map<String,Object>> redisStatMap=new HashMap<RedisConstant,Map<String,Object>>();
  String[] data=statResult.split("\r\n");
  String key;
  int i=0;
  int length=data.length;
  while (i < length) {
    if (data[i].contains("#")) {
      int index=data[i].indexOf('#');
      key=data[i].substring(index + 1);
      ++i;
      RedisConstant redisConstant=RedisConstant.value(key.trim());
      if (redisConstant == null) {
        continue;
      }
      Map<String,Object> sectionMap=new LinkedHashMap<String,Object>();
      while (i < length && data[i].contains(":")) {
        String[] pair=StringUtils.splitByWholeSeparator(data[i],":");
        sectionMap.put(pair[0],pair[1]);
        i++;
      }
      redisStatMap.put(redisConstant,sectionMap);
    }
 else {
      i++;
    }
  }
  return redisStatMap;
}
