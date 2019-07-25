/** 
 * Refreshes the ClusterInfo in a blocking fashion
 */
public final ClusterInfo refresh(){
  if (logger.isTraceEnabled()) {
    logger.trace("Performing ClusterInfoUpdateJob");
  }
  final CountDownLatch nodeLatch=updateNodeStats(new ActionListener<NodesStatsResponse>(){
    @Override public void onResponse(    NodesStatsResponse nodeStatses){
      ImmutableOpenMap.Builder<String,DiskUsage> newLeastAvaiableUsages=ImmutableOpenMap.builder();
      ImmutableOpenMap.Builder<String,DiskUsage> newMostAvaiableUsages=ImmutableOpenMap.builder();
      fillDiskUsagePerNode(logger,nodeStatses.getNodes(),newLeastAvaiableUsages,newMostAvaiableUsages);
      leastAvailableSpaceUsages=newLeastAvaiableUsages.build();
      mostAvailableSpaceUsages=newMostAvaiableUsages.build();
    }
    @Override public void onFailure(    Exception e){
      if (e instanceof ReceiveTimeoutTransportException) {
        logger.error("NodeStatsAction timed out for ClusterInfoUpdateJob",e);
      }
 else {
        if (e instanceof ClusterBlockException) {
          if (logger.isTraceEnabled()) {
            logger.trace("Failed to execute NodeStatsAction for ClusterInfoUpdateJob",e);
          }
        }
 else {
          logger.warn("Failed to execute NodeStatsAction for ClusterInfoUpdateJob",e);
        }
        leastAvailableSpaceUsages=ImmutableOpenMap.of();
        mostAvailableSpaceUsages=ImmutableOpenMap.of();
      }
    }
  }
);
  final CountDownLatch indicesLatch=updateIndicesStats(new ActionListener<IndicesStatsResponse>(){
    @Override public void onResponse(    IndicesStatsResponse indicesStatsResponse){
      ShardStats[] stats=indicesStatsResponse.getShards();
      ImmutableOpenMap.Builder<String,Long> newShardSizes=ImmutableOpenMap.builder();
      ImmutableOpenMap.Builder<ShardRouting,String> newShardRoutingToDataPath=ImmutableOpenMap.builder();
      buildShardLevelInfo(logger,stats,newShardSizes,newShardRoutingToDataPath,clusterService.state());
      shardSizes=newShardSizes.build();
      shardRoutingToDataPath=newShardRoutingToDataPath.build();
    }
    @Override public void onFailure(    Exception e){
      if (e instanceof ReceiveTimeoutTransportException) {
        logger.error("IndicesStatsAction timed out for ClusterInfoUpdateJob",e);
      }
 else {
        if (e instanceof ClusterBlockException) {
          if (logger.isTraceEnabled()) {
            logger.trace("Failed to execute IndicesStatsAction for ClusterInfoUpdateJob",e);
          }
        }
 else {
          logger.warn("Failed to execute IndicesStatsAction for ClusterInfoUpdateJob",e);
        }
        shardSizes=ImmutableOpenMap.of();
        shardRoutingToDataPath=ImmutableOpenMap.of();
      }
    }
  }
);
  try {
    nodeLatch.await(fetchTimeout.getMillis(),TimeUnit.MILLISECONDS);
  }
 catch (  InterruptedException e) {
    Thread.currentThread().interrupt();
    logger.warn("Failed to update node information for ClusterInfoUpdateJob within {} timeout",fetchTimeout);
  }
  try {
    indicesLatch.await(fetchTimeout.getMillis(),TimeUnit.MILLISECONDS);
  }
 catch (  InterruptedException e) {
    Thread.currentThread().interrupt();
    logger.warn("Failed to update shard information for ClusterInfoUpdateJob within {} timeout",fetchTimeout);
  }
  ClusterInfo clusterInfo=getClusterInfo();
  try {
    listener.accept(clusterInfo);
  }
 catch (  Exception e) {
    logger.info("Failed executing ClusterInfoService listener",e);
  }
  return clusterInfo;
}
