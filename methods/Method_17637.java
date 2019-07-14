@Override public void record(long key){
  if (tinyCache.contains(key)) {
    policyStats.recordHit();
  }
 else {
    boolean evicted=tinyCache.addItem(key);
    policyStats.recordMiss();
    if (evicted) {
      policyStats.recordEviction();
    }
  }
}
