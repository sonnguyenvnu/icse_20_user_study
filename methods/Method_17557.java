@Override public void finished(){
  cache.close();
  policyStats.addEvictions(cache.statistics().getEvictionCount());
}
