/** 
 * Moves the entry to the MRU position. 
 */
private void onProtectedHit(Node node){
  admittor.record(node.key);
  node.moveToTail(headProtected);
}
