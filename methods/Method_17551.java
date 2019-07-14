@Override public void finished(){
  CacheStats stats=cache.stats();
  checkState(policyStats.hitCount() == stats.getHits());
  checkState(policyStats.missCount() == stats.getMisses());
  checkState(policyStats.evictionCount() == stats.getEvictions());
}
