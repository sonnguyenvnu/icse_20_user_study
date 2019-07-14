@Override public Map<String,Long> getErrorInfos(){
  Map<String,Long> longMap=ErrorStatisticsAppender.ERROR_NAME_VALUE_MAP.asMap();
  Map<String,Long> resultMap=new LinkedHashMap<String,Long>();
  SortedMap<Long,List<String>> sortedMap=new TreeMap<Long,List<String>>();
  for (  Map.Entry<String,Long> entry : longMap.entrySet()) {
    String key=entry.getKey();
    Long num=entry.getValue();
    if (num == 0L) {
      continue;
    }
    if (sortedMap.containsKey(num)) {
      sortedMap.get(num).add(key);
    }
 else {
      List<String> keys=new ArrayList<String>();
      keys.add(key);
      sortedMap.put(num,keys);
    }
  }
  List<Long> keys=new ArrayList<Long>(sortedMap.keySet());
  Collections.reverse(keys);
  for (  Long num : keys) {
    for (    String key : sortedMap.get(num)) {
      resultMap.put(key,num);
    }
  }
  return resultMap;
}
