/** 
 * Asynchronously computes or retrieves a replacement value corresponding to an already-cached {@code key}. If the replacement value is not found then the mapping will be removed if {@code null} is computed. This method is called when an existing cache entry is refreshed by{@link Caffeine#refreshAfterWrite}, or through a call to  {@link LoadingCache#refresh}. <p> <b>Note:</b> <i>all exceptions thrown by this method will be logged and then swallowed</i>.
 * @param key the non-null key whose value should be loaded
 * @param oldValue the non-null old value corresponding to {@code key}
 * @param executor the executor with which the entry is asynchronously loaded
 * @return a future containing the new value associated with {@code key}, or containing {@code null} if the mapping is to be removed
 */
@NonNull default CompletableFuture<V> asyncReload(@NonNull K key,@NonNull V oldValue,@NonNull Executor executor){
  return asyncLoad(key,executor);
}
