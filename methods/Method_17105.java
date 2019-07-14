@Override public Map<K,V> getAllPresent(Iterable<?> keys){
  Set<Object> uniqueKeys=new LinkedHashSet<>();
  for (  Object key : keys) {
    uniqueKeys.add(key);
  }
  int misses=0;
  long now=expirationTicker().read();
  Map<Object,Object> result=new LinkedHashMap<>(uniqueKeys.size());
  for (  Object key : uniqueKeys) {
    V value;
    Node<K,V> node=data.get(nodeFactory.newLookupKey(key));
    if ((node == null) || ((value=node.getValue()) == null) || hasExpired(node,now)) {
      misses++;
    }
 else {
      result.put(key,value);
      if (!isComputingAsync(node)) {
        @SuppressWarnings("unchecked") K castedKey=(K)key;
        tryExpireAfterRead(node,castedKey,value,expiry(),now);
        setAccessTime(node,now);
      }
      afterRead(node,now,false);
    }
  }
  statsCounter().recordMisses(misses);
  statsCounter().recordHits(result.size());
  @SuppressWarnings("unchecked") Map<K,V> castedResult=(Map<K,V>)result;
  return Collections.unmodifiableMap(castedResult);
}
