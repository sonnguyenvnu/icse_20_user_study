@Override public Map<JobInstance,List<Integer>> sharding(final List<JobInstance> jobInstances,final String jobName,final int shardingTotalCount){
  if (jobInstances.isEmpty()) {
    return Collections.emptyMap();
  }
  Map<JobInstance,List<Integer>> result=shardingAliquot(jobInstances,shardingTotalCount);
  addAliquant(jobInstances,shardingTotalCount,result);
  return result;
}
