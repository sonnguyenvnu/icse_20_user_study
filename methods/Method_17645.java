/** 
 * Moves the entry to the MRU position, if it falls outside of the fast-path threshold. 
 */
private void onProtectedHit(Node node){
  admittor.record(node.key);
  node.moveToTail(headProtected);
}
