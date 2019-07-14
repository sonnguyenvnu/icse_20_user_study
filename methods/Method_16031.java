@Override protected Collection<CacheOperation> findCacheOperations(final Class<?> clazz){
  return determineCacheOperations(parser -> parser.parseCacheAnnotations(clazz));
}
