Object readResolve(){
  Caffeine<Object,Object> builder=recreateCaffeine();
  if (async) {
    if (loader == null) {
      return builder.buildAsync();
    }
    @SuppressWarnings("unchecked") AsyncCacheLoader<K,V> cacheLoader=(AsyncCacheLoader<K,V>)loader;
    return builder.buildAsync(cacheLoader);
  }
  if (loader == null) {
    return builder.build();
  }
  @SuppressWarnings("unchecked") CacheLoader<K,V> cacheLoader=(CacheLoader<K,V>)loader;
  return builder.build(cacheLoader);
}
