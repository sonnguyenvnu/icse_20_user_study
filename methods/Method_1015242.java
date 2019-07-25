@Override public SortedMap<K,V> intersection(ISet<K> keys){
  SortedMap<K,V> result=(SortedMap<K,V>)Maps.intersection(new SortedMap<K,V>().linear(),this,keys);
  return isLinear() ? result : result.forked();
}
