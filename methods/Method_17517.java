private void evict(Node candidate){
  if (data.size() > maximumSize) {
    Node victim=(maxProtected == 0) ? headProtected.next : headProbation.next;
    policyStats.recordEviction();
    boolean admit=admittor.admit(candidate.key,victim.key);
    if (admit) {
      evictEntry(victim);
    }
 else {
      evictEntry(candidate);
    }
  }
}
