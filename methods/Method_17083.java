/** 
 * Evicts entries from the main space if the cache exceeds the maximum capacity. The main space determines whether admitting an entry (coming from the window space) is preferable to retaining the eviction policy's victim. This is decision is made using a frequency filter so that the least frequently used entry is removed. The window space candidates were previously placed in the MRU position and the eviction policy's victim is at the LRU position. The two ends of the queue are evaluated while an eviction is required. The number of remaining candidates is provided and decremented on eviction, so that when there are no more candidates the victim is evicted.
 * @param candidates the number of candidate entries evicted from the window space
 */
@GuardedBy("evictionLock") void evictFromMain(int candidates){
  int victimQueue=PROBATION;
  Node<K,V> victim=accessOrderProbationDeque().peekFirst();
  Node<K,V> candidate=accessOrderProbationDeque().peekLast();
  while (weightedSize() > maximum()) {
    if (candidates == 0) {
      candidate=null;
    }
    if ((candidate == null) && (victim == null)) {
      if (victimQueue == PROBATION) {
        victim=accessOrderProtectedDeque().peekFirst();
        victimQueue=PROTECTED;
        continue;
      }
 else       if (victimQueue == PROTECTED) {
        victim=accessOrderWindowDeque().peekFirst();
        victimQueue=WINDOW;
        continue;
      }
      break;
    }
    if ((victim != null) && (victim.getPolicyWeight() == 0)) {
      victim=victim.getNextInAccessOrder();
      continue;
    }
 else     if ((candidate != null) && (candidate.getPolicyWeight() == 0)) {
      candidate=candidate.getPreviousInAccessOrder();
      candidates--;
      continue;
    }
    if (victim == null) {
      @SuppressWarnings("NullAway") Node<K,V> previous=candidate.getPreviousInAccessOrder();
      Node<K,V> evict=candidate;
      candidate=previous;
      candidates--;
      evictEntry(evict,RemovalCause.SIZE,0L);
      continue;
    }
 else     if (candidate == null) {
      Node<K,V> evict=victim;
      victim=victim.getNextInAccessOrder();
      evictEntry(evict,RemovalCause.SIZE,0L);
      continue;
    }
    K victimKey=victim.getKey();
    K candidateKey=candidate.getKey();
    if (victimKey == null) {
      @NonNull Node<K,V> evict=victim;
      victim=victim.getNextInAccessOrder();
      evictEntry(evict,RemovalCause.COLLECTED,0L);
      continue;
    }
 else     if (candidateKey == null) {
      candidates--;
      @NonNull Node<K,V> evict=candidate;
      candidate=candidate.getPreviousInAccessOrder();
      evictEntry(evict,RemovalCause.COLLECTED,0L);
      continue;
    }
    if (candidate.getPolicyWeight() > maximum()) {
      candidates--;
      Node<K,V> evict=candidate;
      candidate=candidate.getPreviousInAccessOrder();
      evictEntry(evict,RemovalCause.SIZE,0L);
      continue;
    }
    candidates--;
    if (admit(candidateKey,victimKey)) {
      Node<K,V> evict=victim;
      victim=victim.getNextInAccessOrder();
      evictEntry(evict,RemovalCause.SIZE,0L);
      candidate=candidate.getPreviousInAccessOrder();
    }
 else {
      Node<K,V> evict=candidate;
      candidate=candidate.getPreviousInAccessOrder();
      evictEntry(evict,RemovalCause.SIZE,0L);
    }
  }
}
