@Override public void record(long key){
  Object value=cache.get(key);
  if (value == null) {
    cache.put(key,key);
    policyStats.recordMiss();
  }
 else {
    policyStats.recordHit();
  }
}
