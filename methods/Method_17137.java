/** 
 * Builds a cache which does not automatically load values when keys are requested unless a mapping function is provided. The returned  {@link CompletableFuture} may be already loaded orcurrently computing the value for a given key. If the asynchronous computation fails or computes a  {@code null} value then the entry will be automatically removed. Note that multiplethreads can concurrently load values for distinct keys. <p> Consider  {@link #buildAsync(CacheLoader)} or {@link #buildAsync(AsyncCacheLoader)} instead, ifit is feasible to implement an  {@code CacheLoader} or {@code AsyncCacheLoader}. <p> This method does not alter the state of this  {@code Caffeine} instance, so it can be invokedagain to create multiple independent caches. <p> This construction cannot be used with  {@link #weakValues()},  {@link #softValues()}, or {@link #writer(CacheWriter)}.
 * @param < K1 > the key type of the cache
 * @param < V1 > the value type of the cache
 * @return a cache having the requested features
 */
@NonNull public <K1 extends K,V1 extends V>AsyncCache<K1,V1> buildAsync(){
  requireState(valueStrength == null,"Weak or soft values can not be combined with AsyncCache");
  requireState(writer == null,"CacheWriter can not be combined with AsyncCache");
  requireWeightWithWeigher();
  requireNonLoadingCache();
  @SuppressWarnings("unchecked") Caffeine<K1,V1> self=(Caffeine<K1,V1>)this;
  return isBounded() ? new BoundedLocalCache.BoundedLocalAsyncCache<>(self) : new UnboundedLocalCache.UnboundedLocalAsyncCache<>(self);
}
