/** 
 * ??????????????.
 * @param shardingItems ???
 * @return ?????
 */
public ShardingContexts getJobShardingContext(final List<Integer> shardingItems){
  LiteJobConfiguration liteJobConfig=configService.load(false);
  removeRunningIfMonitorExecution(liteJobConfig.isMonitorExecution(),shardingItems);
  if (shardingItems.isEmpty()) {
    return new ShardingContexts(buildTaskId(liteJobConfig,shardingItems),liteJobConfig.getJobName(),liteJobConfig.getTypeConfig().getCoreConfig().getShardingTotalCount(),liteJobConfig.getTypeConfig().getCoreConfig().getJobParameter(),Collections.<Integer,String>emptyMap());
  }
  Map<Integer,String> shardingItemParameterMap=new ShardingItemParameters(liteJobConfig.getTypeConfig().getCoreConfig().getShardingItemParameters()).getMap();
  return new ShardingContexts(buildTaskId(liteJobConfig,shardingItems),liteJobConfig.getJobName(),liteJobConfig.getTypeConfig().getCoreConfig().getShardingTotalCount(),liteJobConfig.getTypeConfig().getCoreConfig().getJobParameter(),getAssignedShardingItemParameterMap(shardingItems,shardingItemParameterMap));
}
