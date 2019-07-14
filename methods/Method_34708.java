/** 
 * Put the Future in the cache if it does not already exist. <p> If this method returns a non-null value then another thread won the race and it should be returned instead of proceeding with execution of the new Future.
 * @param cacheKey key as defined by  {@link HystrixCommand#getCacheKey()}
 * @param f Future to be cached
 * @return null if nothing else was in the cache (or this {@link HystrixCommand} does not have a cacheKey) or previous value if another thread beat us to adding to the cache
 */
@SuppressWarnings({"unchecked"}) <T>HystrixCachedObservable<T> putIfAbsent(String cacheKey,HystrixCachedObservable<T> f){
  ValueCacheKey key=getRequestCacheKey(cacheKey);
  if (key != null) {
    ConcurrentHashMap<ValueCacheKey,HystrixCachedObservable<?>> cacheInstance=requestVariableForCache.get(concurrencyStrategy);
    if (cacheInstance == null) {
      throw new IllegalStateException("Request caching is not available. Maybe you need to initialize the HystrixRequestContext?");
    }
    HystrixCachedObservable<T> alreadySet=(HystrixCachedObservable<T>)cacheInstance.putIfAbsent(key,f);
    if (alreadySet != null) {
      return alreadySet;
    }
  }
  return null;
}
