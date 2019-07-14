/** 
 * Promotes the entry to the protected region's MRU position, demoting an entry if necessary. 
 */
private void onMainProbationHit(Node node){
  node.remove();
  node.status=Status.MAIN_PROTECTED;
  node.appendToTail(headMainProtected);
  sizeMainProtected++;
  if (sizeMainProtected > maxMainProtected) {
    Node demote=headMainProtected.next;
    demote.remove();
    demote.status=Status.MAIN_PROBATION;
    demote.appendToTail(headMainProbation);
    sizeMainProtected--;
  }
}
