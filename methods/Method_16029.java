/** 
 * Validates the specified  {@link CacheOperation}. <p>Throws an  {@link IllegalStateException} if the state of the operation isinvalid. As there might be multiple sources for default values, this ensure that the operation is in a proper state before being returned.
 * @param ae        the annotated element of the cache operation
 * @param operation the {@link CacheOperation} to validate
 */
private void validateCacheOperation(AnnotatedElement ae,CacheOperation operation){
  if (StringUtils.hasText(operation.getKey()) && StringUtils.hasText(operation.getKeyGenerator())) {
    throw new IllegalStateException("Invalid cache annotation configuration on '" + ae.toString() + "'. Both 'key' and 'keyGenerator' attributes have been set. " + "These attributes are mutually exclusive: either set the SpEL expression used to" + "compute the key at runtime or set the name of the KeyGenerator bean to use.");
  }
  if (StringUtils.hasText(operation.getCacheManager()) && StringUtils.hasText(operation.getCacheResolver())) {
    throw new IllegalStateException("Invalid cache annotation configuration on '" + ae.toString() + "'. Both 'cacheManager' and 'cacheResolver' attributes have been set. " + "These attributes are mutually exclusive: the cache manager is used to configure a" + "default cache resolver if none is set. If a cache resolver is set, the cache manager" + "won't be used.");
  }
}
