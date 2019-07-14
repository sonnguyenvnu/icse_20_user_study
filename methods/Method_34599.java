private Observable<R> handleThreadPoolRejectionViaFallback(Exception underlying){
  eventNotifier.markEvent(HystrixEventType.THREAD_POOL_REJECTED,commandKey);
  threadPool.markThreadRejection();
  return getFallbackOrThrowException(this,HystrixEventType.THREAD_POOL_REJECTED,FailureType.REJECTED_THREAD_EXECUTION,"could not be queued for execution",underlying);
}
