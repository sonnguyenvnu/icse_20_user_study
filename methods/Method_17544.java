@Override public void record(long key){
  Object value=cache.peek(key);
  if (value == null) {
    policyStats.recordMiss();
    if (cache.asMap().size() == maximumSize) {
      policyStats.recordEviction();
    }
    cache.put(key,key);
  }
 else {
    policyStats.recordHit();
  }
}
