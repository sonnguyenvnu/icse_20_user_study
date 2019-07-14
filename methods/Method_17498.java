/** 
 * Evicts while the map exceeds the maximum capacity. 
 */
private void evict(Node candidate){
  if (data.size() > maximumSize) {
    Node victim=nextVictim(candidate);
    boolean admit=admittor.admit(candidate.key,victim.key);
    if (admit) {
      evictEntry(victim);
    }
 else {
      evictEntry(candidate);
    }
    policyStats.recordEviction();
  }
}
