@Override default Map<K,V> getAll(Iterable<? extends K> keys,Function<Iterable<? extends K>,Map<K,V>> mappingFunction){
  Set<K> keysToLoad=new LinkedHashSet<>();
  Map<K,V> found=getAllPresent(keys);
  Map<K,V> result=new LinkedHashMap<>(found.size());
  for (  K key : keys) {
    V value=found.get(key);
    if (value == null) {
      keysToLoad.add(key);
    }
    result.put(key,value);
  }
  if (keysToLoad.isEmpty()) {
    return found;
  }
  bulkLoad(keysToLoad,result,mappingFunction);
  return Collections.unmodifiableMap(result);
}
