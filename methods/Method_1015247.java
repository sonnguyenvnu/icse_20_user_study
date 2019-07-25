@Override public SortedMap<K,V> forked(){
  return isLinear() ? new SortedMap<>(root,false,comparator) : this;
}
