private static long computeDelayForRepeatedErrors(JobStore jobStore,int acquiresFailed){
  long delay;
  try {
    delay=jobStore.getAcquireRetryDelay(acquiresFailed);
  }
 catch (  Exception ignored) {
    delay=100;
  }
  if (delay < MIN_DELAY)   delay=MIN_DELAY;
  if (delay > MAX_DELAY)   delay=MAX_DELAY;
  return delay;
}
