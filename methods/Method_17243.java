/** 
 * Removes the entry from its bucket, if scheduled. 
 */
void unlink(Node<K,V> node){
  Node<K,V> next=node.getNextInVariableOrder();
  if (next != null) {
    Node<K,V> prev=node.getPreviousInVariableOrder();
    next.setPreviousInVariableOrder(prev);
    prev.setNextInVariableOrder(next);
  }
}
