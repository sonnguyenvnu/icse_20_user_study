/** 
 * Returns a mapping function that adapts to  {@link CacheLoader#loadAll}, if implemented. 
 */
static <K,V>@Nullable Function<Iterable<? extends K>,Map<K,V>> newBulkMappingFunction(CacheLoader<? super K,V> cacheLoader){
  if (!hasLoadAll(cacheLoader)) {
    return null;
  }
  return keysToLoad -> {
    try {
      @SuppressWarnings("unchecked") Map<K,V> loaded=(Map<K,V>)cacheLoader.loadAll(keysToLoad);
      return loaded;
    }
 catch (    RuntimeException e) {
      throw e;
    }
catch (    InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new CompletionException(e);
    }
catch (    Exception e) {
      throw new CompletionException(e);
    }
  }
;
}
