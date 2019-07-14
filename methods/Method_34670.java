public long getCumulativeCount(HystrixEventType.Collapser collapserEventType){
  return cumulativeCollapserEventCounterStream.getLatest(collapserEventType);
}
