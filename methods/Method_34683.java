public long getRollingCount(HystrixEventType eventType){
  return rollingCommandEventCounterStream.getLatest(eventType);
}
