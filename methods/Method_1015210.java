@Override public Map<K,V> intersection(ISet<K> keys){
  if (keys instanceof Set && Maps.equivEquality(this,keys)) {
    return intersection(((Set<K>)keys).map);
  }
 else {
    Map<K,V> map=(Map<K,V>)Maps.intersection(new Map<K,V>().linear(),this,keys);
    return isLinear() ? map : map.forked();
  }
}
