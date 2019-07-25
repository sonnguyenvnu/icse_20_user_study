private RecoveryResponse recover(final StartRecoveryRequest request) throws IOException {
  final IndexService indexService=indicesService.indexServiceSafe(request.shardId().getIndex());
  final IndexShard shard=indexService.getShard(request.shardId().id());
  final ShardRouting routingEntry=shard.routingEntry();
  if (routingEntry.primary() == false || routingEntry.active() == false) {
    throw new DelayRecoveryException("source shard [" + routingEntry + "] is not an active primary");
  }
  if (request.isPrimaryRelocation() && (routingEntry.relocating() == false || routingEntry.relocatingNodeId().equals(request.targetNode().getId()) == false)) {
    logger.debug("delaying recovery of {} as source shard is not marked yet as relocating to {}",request.shardId(),request.targetNode());
    throw new DelayRecoveryException("source shard is not marked yet as relocating to [" + request.targetNode() + "]");
  }
  RecoverySourceHandler handler=ongoingRecoveries.addNewRecovery(request,shard);
  logger.trace("[{}][{}] starting recovery to {}",request.shardId().getIndex().getName(),request.shardId().id(),request.targetNode());
  try {
    return handler.recoverToTarget();
  }
  finally {
    ongoingRecoveries.remove(shard,handler);
  }
}
