public Collection<Map.Entry<K,V>> getAll(){
  return new ArrayList<Map.Entry<K,V>>(cacheMap.entrySet());
}
