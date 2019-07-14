@Override public void evictCacheData(final String cachePath){
  TreeCache cache=caches.remove(cachePath + "/");
  if (null != cache) {
    cache.close();
  }
}
