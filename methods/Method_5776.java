@Override public synchronized @Nullable SimpleCacheSpan startReadWriteNonBlocking(String key,long position) throws CacheException {
  Assertions.checkState(!released);
  SimpleCacheSpan cacheSpan=getSpan(key,position);
  if (cacheSpan.isCached) {
    try {
      SimpleCacheSpan newCacheSpan=index.get(key).touch(cacheSpan);
      notifySpanTouched(cacheSpan,newCacheSpan);
      return newCacheSpan;
    }
 catch (    CacheException e) {
      return cacheSpan;
    }
  }
  CachedContent cachedContent=index.getOrAdd(key);
  if (!cachedContent.isLocked()) {
    cachedContent.setLocked(true);
    return cacheSpan;
  }
  return null;
}
