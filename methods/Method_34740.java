public long getLatestCount(HystrixEventType.ThreadPool eventType){
  return getLatest()[eventType.ordinal()];
}
