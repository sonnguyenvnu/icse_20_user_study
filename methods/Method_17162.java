/** 
 * Returns a future that waits for all of the dependent futures to complete and returns the combined mapping if successful. If any future fails then it is automatically removed from the cache if still present.
 */
default CompletableFuture<Map<K,V>> composeResult(Map<K,CompletableFuture<V>> futures){
  if (futures.isEmpty()) {
    return CompletableFuture.completedFuture(Collections.emptyMap());
  }
  @SuppressWarnings("rawtypes") CompletableFuture<?>[] array=futures.values().toArray(new CompletableFuture[0]);
  return CompletableFuture.allOf(array).thenApply(ignored -> {
    Map<K,V> result=new LinkedHashMap<>(futures.size());
    futures.forEach((key,future) -> {
      V value=future.getNow(null);
      if (value != null) {
        result.put(key,value);
      }
    }
);
    return Collections.unmodifiableMap(result);
  }
);
}
