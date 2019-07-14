private void weakKeys_weakValues(){
  Cache<Integer,Integer> caffeine=builder().weakKeys().weakValues().build();
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().weakKeys().weakValues().build();
  compare("Weak Keys & Weak Values",caffeine,guava);
}
