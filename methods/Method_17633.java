/** 
 * Moves the entry to the MRU position in the admission window. 
 */
private void onWindowHit(Node node){
  node.moveToTail(headWindow);
}
