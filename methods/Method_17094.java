void setWriteTime(Node<K,V> node,long now){
  if (expiresAfterWrite() || refreshAfterWrite()) {
    node.setWriteTime(now);
  }
}
