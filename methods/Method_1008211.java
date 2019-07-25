public void execute() throws Exception {
  final ShardRouting primaryRouting=primary.routingEntry();
  final ShardId primaryId=primaryRouting.shardId();
  totalShards.incrementAndGet();
  pendingActions.incrementAndGet();
  primaryResult=primary.perform(request);
  successfulShards.incrementAndGet();
  decPendingAndFinishIfNeeded();
}
