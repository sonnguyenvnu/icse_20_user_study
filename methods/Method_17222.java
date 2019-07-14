@Override public void recordLoadSuccess(long loadTime){
  loadSuccessCount.increment();
  totalLoadTime.add(loadTime);
}
