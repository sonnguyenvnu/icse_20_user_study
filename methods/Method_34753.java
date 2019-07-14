public long getLatestRollingMax(){
  startCachingStreamValuesIfUnstarted();
  if (rollingMax.hasValue()) {
    return rollingMax.getValue();
  }
 else {
    return 0L;
  }
}
