private void maximumSize_expireAfterAccess(){
  Cache<Integer,Integer> caffeine=builder().expireAfterAccess(1,TimeUnit.MINUTES).maximumSize(MAXIMUM_SIZE).build();
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().expireAfterAccess(1,TimeUnit.MINUTES).maximumSize(MAXIMUM_SIZE).build();
  compare("Maximum Size & Expire after Access",caffeine,guava);
}
