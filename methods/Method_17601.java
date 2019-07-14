/** 
 * Adds the entry, evicting if necessary. 
 */
private void onMiss(long key){
  for (int i=0; i < gain; i++) {
    admittor.record(key);
  }
  Node node=new Node(key);
  node.appendToTail(head);
  data.put(key,node);
  evict(node);
}
