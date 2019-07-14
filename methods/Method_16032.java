/** 
 * Determine the cache operation(s) for the given  {@link CacheOperationProvider}. <p>This implementation delegates to configured {@link CacheAnnotationParser}s for parsing known annotations into Spring's metadata attribute class. <p>Can be overridden to support custom annotations that carry caching metadata.
 * @param provider the cache operation provider to use
 * @return the configured caching operations, or {@code null} if none found
 */
protected Collection<CacheOperation> determineCacheOperations(CacheOperationProvider provider){
  Collection<CacheOperation> ops=null;
  for (  FixUseSupperClassCacheAnnotationParser annotationParser : this.annotationParsers) {
    Collection<CacheOperation> annOps=provider.getCacheOperations(annotationParser);
    if (annOps != null) {
      if (ops == null) {
        ops=new ArrayList<>();
      }
      ops.addAll(annOps);
    }
  }
  return ops;
}
