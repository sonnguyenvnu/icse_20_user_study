static <K,V>IMap<K,V> difference(IMap<K,V> map,ISet<K> keys){
  for (  K key : keys) {
    map=map.remove(key);
  }
  return map;
}
