@Override default CompletableFuture<Map<K,V>> getAll(Iterable<? extends @NonNull K> keys,Function<Iterable<? extends K>,Map<K,V>> mappingFunction){
  requireNonNull(mappingFunction);
  return getAll(keys,(keysToLoad,executor) -> CompletableFuture.supplyAsync(() -> mappingFunction.apply(keysToLoad),executor));
}
