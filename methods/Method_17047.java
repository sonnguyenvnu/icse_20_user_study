/** 
 * Returns a view of the entries stored in this cache as a thread-safe map. Modifications made to the map directly affect the cache. <p> Iterators from the returned map are at least <i>weakly consistent</i>: they are safe for concurrent use, but if the cache is modified (including by eviction) after the iterator is created, it is undefined which of the changes (if any) will be reflected in that iterator.
 * @return a thread-safe view of this cache supporting all of the optional {@link Map} operations
 */
@Override default @NonNull ConcurrentMap<@NonNull K,@NonNull CompletableFuture<V>> asMap(){
  throw new UnsupportedOperationException();
}
