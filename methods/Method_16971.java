private void setupCaffeine(){
  Cache<Integer,Boolean> cache=Caffeine.newBuilder().build();
  benchmarkFunction=key -> cache.get(key,mappingFunction);
}
