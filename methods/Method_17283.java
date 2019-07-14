@Override public <K,V>CacheProxy<K,V> getCache(String cacheName){
  requireNonNull(cacheName);
  requireNotClosed();
  CacheProxy<?,?> cache=caches.computeIfAbsent(cacheName,name -> {
    CacheProxy<?,?> created=cacheFactory.tryToCreateFromExternalSettings(name);
    if (created != null) {
      created.enableManagement(created.getConfiguration().isManagementEnabled());
      created.enableStatistics(created.getConfiguration().isStatisticsEnabled());
    }
    return created;
  }
);
  @SuppressWarnings("unchecked") CacheProxy<K,V> castedCache=(CacheProxy<K,V>)cache;
  return castedCache;
}
