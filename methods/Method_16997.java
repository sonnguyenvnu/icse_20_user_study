private void weakKeys(){
  Cache<Integer,Integer> caffeine=builder().weakKeys().build();
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().weakKeys().build();
  compare("Weak Keys",caffeine,guava);
}
