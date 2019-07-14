/** 
 * Evicts from the admission window into the probation space. If the size exceeds the maximum, then the admission candidate and probation's victim are evaluated and one is evicted.
 */
private void evict(){
  if (sizeWindow <= maxWindow) {
    return;
  }
  Node candidate=headWindowProbation.next;
  sizeWindow--;
  candidate.remove();
  candidate.status=Status.MAIN_PROBATION;
  candidate.appendToTail(headMainProbation);
  if (data.size() > maximumSize) {
    Node victim=headMainProbation.next;
    Node evict=admittor.admit(candidate.key,victim.key) ? victim : candidate;
    data.remove(evict.key);
    evict.remove();
    policyStats.recordEviction();
  }
}
