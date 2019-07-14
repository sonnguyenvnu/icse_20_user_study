/** 
 * Moves the entry to the MRU position in the admission window. 
 */
private void onWindowProtectedHit(Node node){
  node.moveToTail(headWindowProtected);
}
