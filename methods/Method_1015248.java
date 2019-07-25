@Override public SortedMap<K,V> linear(){
  return isLinear() ? this : new SortedMap<>(root,true,comparator);
}
