public boolean isResponseSemaphoreRejected(){
  return eventCounts.contains(HystrixEventType.SEMAPHORE_REJECTED);
}
