@Override public SortedMap<K,V> slice(K min,K max){
  return new SortedMap<>(root.slice(min,max,comparator),isLinear(),comparator);
}
