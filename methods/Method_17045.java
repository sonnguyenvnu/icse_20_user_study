/** 
 * Asynchronously computes or retrieves the values corresponding to  {@code keys}. This method is called by  {@link AsyncLoadingCache#getAll}. <p> If the returned map doesn't contain all requested  {@code keys} then the entries it does containwill be cached and  {@code getAll} will return the partial results. If the returned map containsextra keys not present in  {@code keys} then all returned entries will be cached, but only theentries for  {@code keys} will be returned from {@code getAll}. <p> This method should be overridden when bulk retrieval is significantly more efficient than many individual lookups. Note that  {@link AsyncLoadingCache#getAll} will defer to individual callsto  {@link AsyncLoadingCache#get} if this method is not overridden.
 * @param keys the unique, non-null keys whose values should be loaded
 * @param executor the executor with which the entries are asynchronously loaded
 * @return a future containing the map from each key in {@code keys} to the value associated withthat key; <b>may not contain null values</b>
 */
@NonNull default CompletableFuture<Map<@NonNull K,@NonNull V>> asyncLoadAll(@NonNull Iterable<? extends @NonNull K> keys,@NonNull Executor executor){
  throw new UnsupportedOperationException();
}
