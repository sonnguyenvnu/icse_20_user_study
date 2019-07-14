@Override public void enableStatistics(String cacheName,boolean enabled){
  requireNotClosed();
  CacheProxy<?,?> cache=caches.get(cacheName);
  if (cache == null) {
    return;
  }
  cache.enableStatistics(enabled);
}
