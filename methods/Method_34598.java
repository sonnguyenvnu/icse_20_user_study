private void cleanUpAfterResponseFromCache(boolean commandExecutionStarted){
  Reference<TimerListener> tl=timeoutTimer.get();
  if (tl != null) {
    tl.clear();
  }
  final long latency=System.currentTimeMillis() - commandStartTimestamp;
  executionResult=executionResult.addEvent(-1,HystrixEventType.RESPONSE_FROM_CACHE).markUserThreadCompletion(latency).setNotExecutedInThread();
  ExecutionResult cacheOnlyForMetrics=ExecutionResult.from(HystrixEventType.RESPONSE_FROM_CACHE).markUserThreadCompletion(latency);
  metrics.markCommandDone(cacheOnlyForMetrics,commandKey,threadPoolKey,commandExecutionStarted);
  eventNotifier.markEvent(HystrixEventType.RESPONSE_FROM_CACHE,commandKey);
}
