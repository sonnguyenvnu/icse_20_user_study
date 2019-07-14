private void setupConcurrentHashMap(){
  ConcurrentMap<Integer,Boolean> map=new ConcurrentHashMap<>();
  benchmarkFunction=key -> map.computeIfAbsent(key,mappingFunction);
}
