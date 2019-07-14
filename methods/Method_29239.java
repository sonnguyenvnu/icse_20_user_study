/** 
 * ??redis ???????
 * @param commandMap
 * @return
 */
private Map<String,Long> transferLongMap(Map<String,Object> commandMap){
  Map<String,Long> resultMap=new HashMap<String,Long>();
  if (commandMap == null || commandMap.isEmpty()) {
    return resultMap;
  }
  for (  String key : commandMap.keySet()) {
    if (commandMap.get(key) == null) {
      continue;
    }
    String value=commandMap.get(key).toString();
    String[] stats=value.split(",");
    if (stats.length == 0) {
      continue;
    }
    String[] calls=stats[0].split("=");
    if (calls == null || calls.length < 2) {
      continue;
    }
    long callCount=Long.valueOf(calls[1]);
    resultMap.put(key,callCount);
  }
  return resultMap;
}
