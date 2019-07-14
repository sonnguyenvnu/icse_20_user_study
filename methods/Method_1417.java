public static InstrumentedMemoryCache<CacheKey,PooledByteBuffer> get(final CountingMemoryCache<CacheKey,PooledByteBuffer> encodedCountingMemoryCache,final ImageCacheStatsTracker imageCacheStatsTracker){
  imageCacheStatsTracker.registerEncodedMemoryCache(encodedCountingMemoryCache);
  MemoryCacheTracker memoryCacheTracker=new MemoryCacheTracker<CacheKey>(){
    @Override public void onCacheHit(    CacheKey cacheKey){
      imageCacheStatsTracker.onMemoryCacheHit(cacheKey);
    }
    @Override public void onCacheMiss(){
      imageCacheStatsTracker.onMemoryCacheMiss();
    }
    @Override public void onCachePut(){
      imageCacheStatsTracker.onMemoryCachePut();
    }
  }
;
  return new InstrumentedMemoryCache<>(encodedCountingMemoryCache,memoryCacheTracker);
}
