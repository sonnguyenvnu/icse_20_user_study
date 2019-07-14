private void expireAfterWrite(){
  Cache<Integer,Integer> caffeine=builder().expireAfterWrite(1,TimeUnit.MINUTES).build();
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().expireAfterWrite(1,TimeUnit.MINUTES).build();
  compare("Expire after Write",caffeine,guava);
}
