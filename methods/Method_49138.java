@Override public void setResultSize(long size){
  metrics.incrementCount(TraversalMetrics.ELEMENT_COUNT_ID,size);
}
