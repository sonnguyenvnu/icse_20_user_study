@Override public void incrementCustom(String metric,long delta){
  AtomicLong counter=customMetrics.get(metric);
  if (counter == null) {
    customMetrics.putIfAbsent(metric,new AtomicLong(0));
    counter=customMetrics.get(metric);
  }
  counter.addAndGet(delta);
  if (log.isDebugEnabled())   log.debug("[{}:{}] Incremented by {}",System.identityHashCode(customMetrics),metric,delta);
}
