private Map<Integer,String> getAssignedShardingItemParameterMap(final List<Integer> shardingItems,final Map<Integer,String> shardingItemParameterMap){
  Map<Integer,String> result=new HashMap<>(shardingItemParameterMap.size(),1);
  for (  int each : shardingItems) {
    result.put(each,shardingItemParameterMap.get(each));
  }
  return result;
}
