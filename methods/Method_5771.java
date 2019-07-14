private void evictCache(Cache cache,long requiredSpace){
  while (currentSize + requiredSpace > maxBytes && !leastRecentlyUsed.isEmpty()) {
    try {
      cache.removeSpan(leastRecentlyUsed.first());
    }
 catch (    CacheException e) {
    }
  }
}
