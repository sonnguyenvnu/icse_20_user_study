private void resetShardingInfo(final int shardingTotalCount){
  for (int i=0; i < shardingTotalCount; i++) {
    jobNodeStorage.removeJobNodeIfExisted(ShardingNode.getInstanceNode(i));
    jobNodeStorage.createJobNodeIfNeeded(ShardingNode.ROOT + "/" + i);
  }
  int actualShardingTotalCount=jobNodeStorage.getJobNodeChildrenKeys(ShardingNode.ROOT).size();
  if (actualShardingTotalCount > shardingTotalCount) {
    for (int i=shardingTotalCount; i < actualShardingTotalCount; i++) {
      jobNodeStorage.removeJobNodeIfExisted(ShardingNode.ROOT + "/" + i);
    }
  }
}
