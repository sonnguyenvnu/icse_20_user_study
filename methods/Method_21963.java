@Override public final void beforeJobExecuted(final ShardingContexts shardingContexts){
  guaranteeService.registerStart(shardingContexts.getShardingItemParameters().keySet());
  if (guaranteeService.isAllStarted()) {
    doBeforeJobExecutedAtLastStarted(shardingContexts);
    guaranteeService.clearAllStartedInfo();
    return;
  }
  long before=timeService.getCurrentMillis();
  try {
synchronized (startedWait) {
      startedWait.wait(startedTimeoutMilliseconds);
    }
  }
 catch (  final InterruptedException ex) {
    Thread.interrupted();
  }
  if (timeService.getCurrentMillis() - before >= startedTimeoutMilliseconds) {
    guaranteeService.clearAllStartedInfo();
    handleTimeout(startedTimeoutMilliseconds);
  }
}
