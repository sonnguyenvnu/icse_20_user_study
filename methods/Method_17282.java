@Override public <K,V,C extends Configuration<K,V>>Cache<K,V> createCache(String cacheName,C configuration){
  requireNotClosed();
  requireNonNull(configuration);
  CacheProxy<?,?> cache=caches.compute(cacheName,(name,existing) -> {
    if ((existing != null) && !existing.isClosed()) {
      throw new CacheException("Cache " + cacheName + " already exists");
    }
 else     if (cacheFactory.isDefinedExternally(cacheName)) {
      throw new CacheException("Cache " + cacheName + " is configured externally");
    }
    return cacheFactory.createCache(cacheName,configuration);
  }
);
  enableManagement(cache.getName(),cache.getConfiguration().isManagementEnabled());
  enableStatistics(cache.getName(),cache.getConfiguration().isStatisticsEnabled());
  @SuppressWarnings("unchecked") Cache<K,V> castedCache=(Cache<K,V>)cache;
  return castedCache;
}
