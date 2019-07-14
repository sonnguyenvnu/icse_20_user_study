private void addAliquant(final List<JobInstance> shardingUnits,final int shardingTotalCount,final Map<JobInstance,List<Integer>> shardingResults){
  int aliquant=shardingTotalCount % shardingUnits.size();
  int count=0;
  for (  Map.Entry<JobInstance,List<Integer>> entry : shardingResults.entrySet()) {
    if (count < aliquant) {
      entry.getValue().add(shardingTotalCount / shardingUnits.size() * shardingUnits.size() + count);
    }
    count++;
  }
}
