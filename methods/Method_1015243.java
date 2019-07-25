@Override public SortedMap<K,V> difference(IMap<K,?> m){
  SortedMap<K,V> result=clone().linear();
  m.keys().forEach(result::remove);
  return isLinear() ? result : result.forked();
}
