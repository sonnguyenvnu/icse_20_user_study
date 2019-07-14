private void maximumSize_expireAfterWrite(){
  Cache<Integer,Integer> caffeine=builder().expireAfterWrite(1,TimeUnit.MINUTES).maximumSize(MAXIMUM_SIZE).build();
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().expireAfterWrite(1,TimeUnit.MINUTES).maximumSize(MAXIMUM_SIZE).build();
  compare("Maximum Size & Expire after Write",caffeine,guava);
}
