@Override default CompletableFuture<V> get(@NonNull K key,@NonNull Function<? super K,? extends V> mappingFunction){
  requireNonNull(mappingFunction);
  return get(key,(k1,executor) -> CompletableFuture.supplyAsync(() -> mappingFunction.apply(key),executor));
}
