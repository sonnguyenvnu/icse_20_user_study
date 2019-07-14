private void expireAfterAccess(){
  Cache<Integer,Integer> caffeine=builder().expireAfterAccess(1,TimeUnit.MINUTES).build();
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().expireAfterAccess(1,TimeUnit.MINUTES).build();
  compare("Expire after Access",caffeine,guava);
}
