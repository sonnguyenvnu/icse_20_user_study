@Override public Map<K,V> getAllPresent(Iterable<?> keys){
  Set<Object> uniqueKeys=new LinkedHashSet<>();
  for (  Object key : keys) {
    uniqueKeys.add(key);
  }
  int misses=0;
  Map<Object,Object> result=new LinkedHashMap<>(uniqueKeys.size());
  for (  Object key : uniqueKeys) {
    Object value=data.get(key);
    if (value == null) {
      misses++;
    }
 else {
      result.put(key,value);
    }
  }
  statsCounter.recordMisses(misses);
  statsCounter.recordHits(result.size());
  @SuppressWarnings("unchecked") Map<K,V> castedResult=(Map<K,V>)result;
  return Collections.unmodifiableMap(castedResult);
}
