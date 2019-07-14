/** 
 * ????????
 * @param statResult
 * @return
 */
private Map<RedisMigrateToolConstant,Map<String,Object>> processRedisMigrateToolStats(String statResult){
  Map<RedisMigrateToolConstant,Map<String,Object>> redisStatMap=new HashMap<RedisMigrateToolConstant,Map<String,Object>>();
  String[] data=statResult.split("\r\n");
  String key;
  int i=0;
  int length=data.length;
  while (i < length) {
    if (data[i].contains("#")) {
      int index=data[i].indexOf('#');
      key=data[i].substring(index + 1);
      ++i;
      RedisMigrateToolConstant redisMigrateToolConstant=RedisMigrateToolConstant.value(key.trim());
      if (redisMigrateToolConstant == null) {
        continue;
      }
      Map<String,Object> sectionMap=new LinkedHashMap<String,Object>();
      while (i < length && data[i].contains(":")) {
        String[] pair=data[i].split(":");
        sectionMap.put(pair[0],pair[1]);
        i++;
      }
      redisStatMap.put(redisMigrateToolConstant,sectionMap);
    }
 else {
      i++;
    }
  }
  return redisStatMap;
}
