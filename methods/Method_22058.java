@Override public ShardingContexts getShardingContexts(){
  boolean isFailover=configService.load(true).isFailover();
  if (isFailover) {
    List<Integer> failoverShardingItems=failoverService.getLocalFailoverItems();
    if (!failoverShardingItems.isEmpty()) {
      return executionContextService.getJobShardingContext(failoverShardingItems);
    }
  }
  shardingService.shardingIfNecessary();
  List<Integer> shardingItems=shardingService.getLocalShardingItems();
  if (isFailover) {
    shardingItems.removeAll(failoverService.getLocalTakeOffItems());
  }
  shardingItems.removeAll(executionService.getDisabledItems(shardingItems));
  return executionContextService.getJobShardingContext(shardingItems);
}
