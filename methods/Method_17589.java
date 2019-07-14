/** 
 * Moves the entry to the MRU position, if it falls outside of the fast-path threshold. 
 */
private void onProtectedHit(Node node){
  node.moveToTail(headProtected);
}
