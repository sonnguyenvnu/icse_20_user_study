@Override public synchronized Iterable<? extends InvocationStatistics> getInvocationStatistics(){
  return METHOD_STATS_MAP.values();
}
