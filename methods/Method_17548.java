@Override public void record(long key){
  Object value=cache.putIfAbsent(key,key);
  if (value == null) {
    size++;
    policyStats.recordMiss();
    if (size > maximumSize) {
      policyStats.recordEviction();
      size--;
    }
  }
 else {
    policyStats.recordHit();
  }
}
