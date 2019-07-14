static <K,V>Node<K,V> traverse(boolean ascending,Node<K,V> node){
  return ascending ? node.getNextInVariableOrder() : node.getPreviousInVariableOrder();
}
