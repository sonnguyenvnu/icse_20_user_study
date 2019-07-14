@Override public void destroyCache(String cacheName){
  requireNotClosed();
  Cache<?,?> cache=caches.remove(cacheName);
  if (cache != null) {
    cache.close();
  }
}
