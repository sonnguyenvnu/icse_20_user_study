static <K,V>IMap<K,V> intersection(IMap<K,V> accumulator,IMap<K,V> map,ISet<K> keys){
  if (map.size() < keys.size()) {
    for (    IEntry<K,V> entry : map.entries()) {
      if (keys.contains(entry.key())) {
        accumulator.put(entry.key(),entry.value());
      }
    }
  }
 else {
    for (    K key : keys) {
      Object value=((IMap)map).get(key,DEFAULT_VALUE);
      if (value != DEFAULT_VALUE) {
        accumulator=accumulator.put(key,(V)value);
      }
    }
  }
  return accumulator;
}
