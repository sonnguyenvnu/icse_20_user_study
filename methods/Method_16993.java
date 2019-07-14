private void maximumSize_refreshAfterWrite(){
  Cache<Integer,Integer> caffeine=builder().refreshAfterWrite(1,TimeUnit.MINUTES).maximumSize(MAXIMUM_SIZE).build(k -> k);
  com.google.common.cache.Cache<Integer,Integer> guava=CacheBuilder.newBuilder().refreshAfterWrite(1,TimeUnit.MINUTES).maximumSize(MAXIMUM_SIZE).build(new CacheLoader<Integer,Integer>(){
    @Override public Integer load(    Integer key){
      return key;
    }
  }
);
  compare("Maximum Size & Refresh after Write",caffeine,guava);
}
