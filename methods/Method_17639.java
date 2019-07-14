@Override public void record(long key){
  if (tinyCache.contains(key)) {
    tinyCache.recordItem(key);
    policyStats.recordHit();
  }
 else {
    boolean evicted=tinyCache.addItem(key);
    tinyCache.recordItem(key);
    policyStats.recordMiss();
    if (evicted) {
      policyStats.recordEviction();
    }
  }
}
