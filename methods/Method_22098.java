/** 
 * ??????????????.
 * @param jobInstanceId ????????
 * @return ????????????
 */
public List<Integer> getShardingItems(final String jobInstanceId){
  JobInstance jobInstance=new JobInstance(jobInstanceId);
  if (!serverService.isAvailableServer(jobInstance.getIp())) {
    return Collections.emptyList();
  }
  List<Integer> result=new LinkedList<>();
  int shardingTotalCount=configService.load(true).getTypeConfig().getCoreConfig().getShardingTotalCount();
  for (int i=0; i < shardingTotalCount; i++) {
    if (jobInstance.getJobInstanceId().equals(jobNodeStorage.getJobNodeData(ShardingNode.getInstanceNode(i)))) {
      result.add(i);
    }
  }
  return result;
}
