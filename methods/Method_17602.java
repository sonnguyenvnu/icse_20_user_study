/** 
 * Moves the entry to the MRU position. 
 */
private void onHit(Node node){
  node.moveToTail(head);
}
