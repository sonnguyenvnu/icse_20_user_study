@Override public Map<JobInstance,List<Integer>> sharding(final List<JobInstance> jobInstances,final String jobName,final int shardingTotalCount){
  return averageAllocationJobShardingStrategy.sharding(rotateServerList(jobInstances,jobName),jobName,shardingTotalCount);
}
