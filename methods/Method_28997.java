@Override public Map<String,Map<String,Long>> getCostTimeGroupByMinuteAndCommand(){
  Map<CostTimeDetailStatKey,AtomicLongMap<Integer>> map=UsefulDataCollector.getDataCostTimeMapAll();
  if (map == null || map.isEmpty()) {
    return Collections.emptyMap();
  }
  Map<String,Map<String,Long>> result=new HashMap<String,Map<String,Long>>();
  for (  Entry<CostTimeDetailStatKey,AtomicLongMap<Integer>> entry : map.entrySet()) {
    String minute=entry.getKey().getCurrentMinute();
    String command=entry.getKey().getCommand();
    Long totalCount=0L;
    for (    Long count : entry.getValue().asMap().values()) {
      totalCount+=count;
    }
    if (result.containsKey(minute)) {
      Map<String,Long> tempMap=result.get(minute);
      if (tempMap.containsKey(command)) {
        tempMap.put(command,tempMap.get(command) + totalCount);
      }
 else {
        tempMap.put(command,totalCount);
      }
      result.put(minute,tempMap);
    }
 else {
      Map<String,Long> tempMap=new HashMap<String,Long>();
      tempMap.put(command,totalCount);
      result.put(minute,tempMap);
    }
  }
  return result;
}
