/** 
 * ???????????????, ?????. <p> ?????????????. </p>
 */
public void shardingIfNecessary(){
  List<JobInstance> availableJobInstances=instanceService.getAvailableJobInstances();
  if (!isNeedSharding() || availableJobInstances.isEmpty()) {
    return;
  }
  if (!leaderService.isLeaderUntilBlock()) {
    blockUntilShardingCompleted();
    return;
  }
  waitingOtherShardingItemCompleted();
  LiteJobConfiguration liteJobConfig=configService.load(false);
  int shardingTotalCount=liteJobConfig.getTypeConfig().getCoreConfig().getShardingTotalCount();
  log.debug("Job '{}' sharding begin.",jobName);
  jobNodeStorage.fillEphemeralJobNode(ShardingNode.PROCESSING,"");
  resetShardingInfo(shardingTotalCount);
  JobShardingStrategy jobShardingStrategy=JobShardingStrategyFactory.getStrategy(liteJobConfig.getJobShardingStrategyClass());
  jobNodeStorage.executeInTransaction(new PersistShardingInfoTransactionExecutionCallback(jobShardingStrategy.sharding(availableJobInstances,jobName,shardingTotalCount)));
  log.debug("Job '{}' sharding complete.",jobName);
}
