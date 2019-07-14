/** 
 * Returns the future of a map of the values associated with  {@code keys}, creating or retrieving those values if necessary. The returned map contains entries that were already cached, combined with newly loaded entries; it will never contain null keys or values. If the any of the asynchronous computations fail, those entries will be automatically removed from this cache. <p> A single request to the  {@code mappingFunction} is performed for all keys which are not alreadypresent in the cache. If another call to  {@link #get} tries to load the value for a key in{@code keys}, that thread retrieves a future that is completed by this bulk computation. Note that multiple threads can concurrently load values for distinct keys. <p> Note that duplicate elements in  {@code keys}, as determined by  {@link Object#equals}, will be ignored.
 * @param keys the keys whose associated values are to be returned
 * @param mappingFunction the function to asynchronously compute the values
 * @return the future containing an unmodifiable mapping of keys to values for the specified keysin this cache
 * @throws NullPointerException if the specified collection is null or contains a null element, orif the future returned by the  {@link AsyncCacheLoader} is null
 * @throws RuntimeException or Error if the {@link AsyncCacheLoader} does so, if{@link AsyncCacheLoader#asyncLoadAll} returns {@code null}, or fails when constructing the future, in which case the mapping is left unestablished
 */
@NonNull default CompletableFuture<Map<K,V>> getAll(@NonNull Iterable<? extends @NonNull K> keys,@NonNull BiFunction<Iterable<? extends @NonNull K>,Executor,CompletableFuture<Map<K,V>>> mappingFunction){
  throw new UnsupportedOperationException();
}
