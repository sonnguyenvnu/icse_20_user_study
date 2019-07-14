/** 
 * Adds the entry to the admission window, evicting if necessary. 
 */
private void onMiss(long key){
  Node node=new Node(key,WINDOW);
  node.appendToTail(headWindow);
  data.put(key,node);
  windowSize++;
  evict();
}
