/** 
 * Schedules a timer event for the node.
 * @param node the entry in the cache
 */
public void schedule(@NonNull Node<K,V> node){
  Node<K,V> sentinel=findBucket(node.getVariableTime());
  link(sentinel,node);
}
