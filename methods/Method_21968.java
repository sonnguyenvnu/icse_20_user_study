private Map<JobInstance,List<Integer>> shardingAliquot(final List<JobInstance> shardingUnits,final int shardingTotalCount){
  Map<JobInstance,List<Integer>> result=new LinkedHashMap<>(shardingTotalCount,1);
  int itemCountPerSharding=shardingTotalCount / shardingUnits.size();
  int count=0;
  for (  JobInstance each : shardingUnits) {
    List<Integer> shardingItems=new ArrayList<>(itemCountPerSharding + 1);
    for (int i=count * itemCountPerSharding; i < (count + 1) * itemCountPerSharding; i++) {
      shardingItems.add(i);
    }
    result.put(each,shardingItems);
    count++;
  }
  return result;
}
