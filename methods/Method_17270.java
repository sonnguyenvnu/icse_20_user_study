@Override public CacheStats snapshot(){
  return new CacheStats(hitCount.getCount(),missCount.getCount(),loadSuccessCount.getCount(),loadFailureCount.getCount(),totalLoadTime.getCount(),evictionCount.getCount(),evictionWeight.getCount());
}
