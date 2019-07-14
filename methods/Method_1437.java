public boolean hasCachedImage(@Nullable CacheKey cacheKey){
  MemoryCache<CacheKey,CloseableImage> memoryCache=mBitmapMemoryCache;
  if (memoryCache == null || cacheKey == null) {
    return false;
  }
  return memoryCache.contains(cacheKey);
}
