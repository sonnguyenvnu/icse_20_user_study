@Override public Map<K,V> difference(IMap<K,?> m){
  if (m instanceof Map && Maps.equivEquality(this,m)) {
    Node<K,V> rootPrime=MapNodes.difference(0,editor,root,((Map)m).root,equalsFn);
    return new Map<>(rootPrime == null ? Node.EMPTY : rootPrime,hashFn,equalsFn,isLinear());
  }
 else {
    return difference(m.keys());
  }
}
