/** 
 * Sequentially loads each missing entry. 
 */
default Map<K,V> loadSequentially(Iterable<? extends K> keys){
  Set<K> uniqueKeys=new LinkedHashSet<>();
  for (  K key : keys) {
    uniqueKeys.add(key);
  }
  int count=0;
  Map<K,V> result=new LinkedHashMap<>(uniqueKeys.size());
  try {
    for (    K key : uniqueKeys) {
      count++;
      V value=get(key);
      if (value != null) {
        result.put(key,value);
      }
    }
  }
 catch (  Throwable t) {
    cache().statsCounter().recordMisses(uniqueKeys.size() - count);
    throw t;
  }
  return Collections.unmodifiableMap(result);
}
