/** 
 * Performs a non-blocking bulk load of the missing keys. Any missing entry that materializes during the load are replaced when the loaded entries are inserted into the cache.
 */
default void bulkLoad(Set<K> keysToLoad,Map<K,V> result,Function<Iterable<? extends @NonNull K>,@NonNull Map<K,V>> mappingFunction){
  boolean success=false;
  long startTime=cache().statsTicker().read();
  try {
    Map<K,V> loaded=mappingFunction.apply(keysToLoad);
    loaded.forEach((key,value) -> cache().put(key,value,false));
    for (    K key : keysToLoad) {
      V value=loaded.get(key);
      if (value == null) {
        result.remove(key);
      }
 else {
        result.put(key,value);
      }
    }
    success=!loaded.isEmpty();
  }
 catch (  RuntimeException e) {
    throw e;
  }
catch (  Exception e) {
    throw new CompletionException(e);
  }
 finally {
    long loadTime=cache().statsTicker().read() - startTime;
    if (success) {
      cache().statsCounter().recordLoadSuccess(loadTime);
    }
 else {
      cache().statsCounter().recordLoadFailure(loadTime);
    }
  }
}
