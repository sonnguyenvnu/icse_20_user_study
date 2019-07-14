/** 
 * Promotes the entry to the protected region's MRU position, demoting an entry if necessary. 
 */
private void onProbationHit(Node node){
  node.remove();
  node.status=Status.PROTECTED;
  node.appendToTail(headProtected);
  sizeProtected++;
  demoteProtected();
}
