@Override public SortedMap<K,V> put(K key,V value,BinaryOperator<V> merge){
  Node<K,V> rootPrime=root.put(key,value,merge,comparator);
  if (isLinear()) {
    hash=-1;
    root=rootPrime;
    return this;
  }
 else {
    return new SortedMap<>(rootPrime,false,comparator);
  }
}
