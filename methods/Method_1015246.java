@Override public SortedMap<K,V> clone(){
  return isLinear() ? forked().linear() : this;
}
