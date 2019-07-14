@Override public long getCustom(String metric){
  AtomicLong counter=customMetrics.get(metric);
  if (counter == null) {
    if (log.isDebugEnabled())     log.debug("[{}:{}] Returning zero by default (was null)",System.identityHashCode(customMetrics),metric);
    return 0;
  }
 else {
    long v=counter.get();
    if (log.isDebugEnabled())     log.debug("[{}:{}] Returning {}",System.identityHashCode(customMetrics),metric,v);
    return v;
  }
}
