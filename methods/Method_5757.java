private static String buildCacheKey(DataSpec dataSpec,@Nullable CacheKeyFactory cacheKeyFactory){
  return (cacheKeyFactory != null ? cacheKeyFactory : DEFAULT_CACHE_KEY_FACTORY).buildCacheKey(dataSpec);
}
