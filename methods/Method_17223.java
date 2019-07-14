@Override public void recordLoadFailure(long loadTime){
  loadFailureCount.increment();
  totalLoadTime.add(loadTime);
}
