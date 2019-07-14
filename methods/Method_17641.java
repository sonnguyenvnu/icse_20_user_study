@Override public void record(long key){
  if (tinyCache.contains(key) || ((window != null) && window.contains(key))) {
    tinyCache.recordItem(key);
    policyStats.recordHit();
  }
 else {
    boolean evicted=tinyCache.addItem(key);
    if (!evicted && (window != null)) {
      evicted=window.addItem(key);
    }
    tinyCache.recordItem(key);
    policyStats.recordMiss();
    if (evicted) {
      policyStats.recordEviction();
    }
  }
}
