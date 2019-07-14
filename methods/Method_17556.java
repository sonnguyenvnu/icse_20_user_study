@Override public void record(long key){
  Object value=cache.get(key);
  if (value == null) {
    policyStats.recordMiss();
    cache.put(key,key);
  }
 else {
    policyStats.recordHit();
  }
}
