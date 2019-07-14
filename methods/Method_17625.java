/** 
 * Evicts if the map exceeds the maximum capacity. 
 */
private void evict(){
  if (sizeWindow <= maxWindow) {
    return;
  }
  Node candidate=headWindow.next;
  candidate.remove();
  sizeWindow--;
  candidate.appendToTail(headMain);
  candidate.status=Status.MAIN;
  sizeMain++;
  if (sizeMain > maxMain) {
    Node victim=headMain.next;
    Node evict=admittor.admit(candidate.key,victim.key) ? victim : candidate;
    data.remove(evict.key);
    evict.remove();
    sizeMain--;
    policyStats.recordEviction();
  }
}
