@Override public boolean remove(@NotNull final CacheKey cacheKey,final boolean cascade){
  checkNotNull(cacheKey,"cacheKey == null");
  boolean result=nextCache().map(new Function<NormalizedCache,Boolean>(){
    @NotNull @Override public Boolean apply(    @NotNull NormalizedCache cache){
      return cache.remove(cacheKey,cascade);
    }
  }
).or(Boolean.FALSE);
  RecordJournal recordJournal=lruCache.getIfPresent(cacheKey.key());
  if (recordJournal != null) {
    lruCache.invalidate(cacheKey.key());
    result=true;
    if (cascade) {
      for (      CacheReference cacheReference : recordJournal.snapshot.referencedFields()) {
        result=result & remove(CacheKey.from(cacheReference.key()),true);
      }
    }
  }
  return result;
}
