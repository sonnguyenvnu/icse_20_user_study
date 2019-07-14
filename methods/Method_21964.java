@Override public final void afterJobExecuted(final ShardingContexts shardingContexts){
  guaranteeService.registerComplete(shardingContexts.getShardingItemParameters().keySet());
  if (guaranteeService.isAllCompleted()) {
    doAfterJobExecutedAtLastCompleted(shardingContexts);
    guaranteeService.clearAllCompletedInfo();
    return;
  }
  long before=timeService.getCurrentMillis();
  try {
synchronized (completedWait) {
      completedWait.wait(completedTimeoutMilliseconds);
    }
  }
 catch (  final InterruptedException ex) {
    Thread.interrupted();
  }
  if (timeService.getCurrentMillis() - before >= completedTimeoutMilliseconds) {
    guaranteeService.clearAllCompletedInfo();
    handleTimeout(completedTimeoutMilliseconds);
  }
}
