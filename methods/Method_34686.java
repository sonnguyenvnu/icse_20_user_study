public long getRollingMaxConcurrentExecutions(){
  return rollingCommandMaxConcurrencyStream.getLatestRollingMax();
}
