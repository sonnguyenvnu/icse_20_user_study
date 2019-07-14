@Override public void recordLoadSuccess(long loadTime){
  loadSuccessCount.inc();
  totalLoadTime.update(loadTime,TimeUnit.NANOSECONDS);
}
