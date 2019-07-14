@Override public void recordLoadFailure(long loadTime){
  loadFailureCount.inc();
  totalLoadTime.update(loadTime,TimeUnit.NANOSECONDS);
}
