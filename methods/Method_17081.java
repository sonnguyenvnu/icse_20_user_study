/** 
 * Evicts entries if the cache exceeds the maximum. 
 */
@GuardedBy("evictionLock") void evictEntries(){
  if (!evicts()) {
    return;
  }
  int candidates=evictFromWindow();
  evictFromMain(candidates);
}
