/** 
 * Adds the entry at the tail of the bucket's list. 
 */
void link(Node<K,V> sentinel,Node<K,V> node){
  node.setPreviousInVariableOrder(sentinel.getPreviousInVariableOrder());
  node.setNextInVariableOrder(sentinel);
  sentinel.getPreviousInVariableOrder().setNextInVariableOrder(node);
  sentinel.setPreviousInVariableOrder(node);
}
