private void removeDataSpec(DataSpec dataSpec){
  CacheUtil.remove(dataSpec,cache,cacheKeyFactory);
}
