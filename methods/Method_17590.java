/** 
 * Evicts from the admission window into the probation space. If the size exceeds the maximum, then the admission candidate and probation's victim are evaluated and one is evicted.
 */
private void evict(){
  if (windowSize <= maxWindow) {
    return;
  }
  Node candidate=headWindow.next;
  windowSize--;
  candidate.remove();
  candidate.queue=PROBATION;
  candidate.appendToTail(headProbation);
  if (data.size() > maximumSize) {
    Node victim=headProbation.next;
    Node evict=admittor.admit(candidate.key,victim.key) ? victim : candidate;
    data.remove(evict.key);
    evict.remove();
    policyStats.recordEviction();
  }
}
