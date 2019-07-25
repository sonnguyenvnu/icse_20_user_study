@Override public SortedMap<K,V> difference(ISet<K> keys){
  SortedMap<K,V> result=clone().linear();
  keys.forEach(result::remove);
  return isLinear() ? result : result.forked();
}
