public long getRollingCount(HystrixEventType.Collapser collapserEventType){
  return rollingCollapserEventCounterStream.getLatest(collapserEventType);
}
