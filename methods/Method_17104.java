@Override public boolean containsKey(Object key){
  Node<K,V> node=data.get(nodeFactory.newLookupKey(key));
  return (node != null) && (node.getValue() != null) && !hasExpired(node,expirationTicker().read());
}
