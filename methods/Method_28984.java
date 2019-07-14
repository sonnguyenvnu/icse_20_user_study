private Map<K,Long> createAsMap(){
  Map<K,Long> resultMap=new LinkedHashMap<K,Long>();
  if (map != null && !map.isEmpty()) {
    for (    Entry<K,AtomicLong> entry : map.entrySet()) {
      resultMap.put(entry.getKey(),entry.getValue().get());
    }
  }
  return Collections.unmodifiableMap(resultMap);
}
