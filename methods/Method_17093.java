void setVariableTime(Node<K,V> node,long expirationTime){
  if (expiresVariable()) {
    node.setVariableTime(expirationTime);
  }
}
