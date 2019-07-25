void warm(Engine.Searcher searcher,IndexShard shard,IndexSettings settings){
  if (shard.state() == IndexShardState.CLOSED) {
    return;
  }
  if (settings.isWarmerEnabled() == false) {
    return;
  }
  if (logger.isTraceEnabled()) {
    logger.trace("{} top warming [{}]",shard.shardId(),searcher.reader());
  }
  shard.warmerService().onPreWarm();
  long time=System.nanoTime();
  final List<TerminationHandle> terminationHandles=new ArrayList<>();
  for (  final Listener listener : listeners) {
    terminationHandles.add(listener.warmReader(shard,searcher));
  }
  for (  TerminationHandle terminationHandle : terminationHandles) {
    try {
      terminationHandle.awaitTermination();
    }
 catch (    InterruptedException e) {
      Thread.currentThread().interrupt();
      logger.warn("top warming has been interrupted",e);
      break;
    }
  }
  long took=System.nanoTime() - time;
  shard.warmerService().onPostWarm(took);
  if (shard.warmerService().logger().isTraceEnabled()) {
    shard.warmerService().logger().trace("top warming took [{}]",new TimeValue(took,TimeUnit.NANOSECONDS));
  }
}
