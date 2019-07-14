private void weakKeys_softValues(){
  Cache<Integer,Integer> caffeine=builder().weakKeys().softValues().build();
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().weakKeys().softValues().build();
  compare("Weak Keys & Soft Values",caffeine,guava);
}
