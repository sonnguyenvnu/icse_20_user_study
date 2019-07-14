@Override public void registerJobCompleted(final ShardingContexts shardingContexts){
  executionService.registerJobCompleted(shardingContexts);
  if (configService.load(true).isFailover()) {
    failoverService.updateFailoverComplete(shardingContexts.getShardingItemParameters().keySet());
  }
}
