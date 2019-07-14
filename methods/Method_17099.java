/** 
 * Updates the node's location in the policy's deque. 
 */
static <K,V>void reorder(LinkedDeque<Node<K,V>> deque,Node<K,V> node){
  if (deque.contains(node)) {
    deque.moveToBack(node);
  }
}
