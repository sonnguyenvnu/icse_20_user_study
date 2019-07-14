public long getRollingCount(HystrixEventType.ThreadPool event){
  return rollingCounterStream.getLatestCount(event);
}
