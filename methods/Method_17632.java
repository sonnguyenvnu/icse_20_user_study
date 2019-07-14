/** 
 * Adds the entry to the admission window, evicting if necessary. 
 */
private void onMiss(long key){
  Node node=new Node(key,Status.WINDOW);
  node.appendToTail(headWindow);
  data.put(key,node);
  sizeWindow++;
  evict();
}
