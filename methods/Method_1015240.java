@Override public IEntry<K,V> ceil(K key){
  Node<K,V> n=root.ceil(key,comparator);
  return n == null ? null : new Maps.Entry<>(n.k,n.v);
}
