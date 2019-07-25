@Override public Map<K,V> clone(){
  return isLinear() ? forked().linear() : this;
}
