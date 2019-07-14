@Nullable private synchronized CacheKey popFirstFreeItemKey(){
  CacheKey cacheKey=null;
  Iterator<CacheKey> iterator=mFreeItemsPool.iterator();
  if (iterator.hasNext()) {
    cacheKey=iterator.next();
    iterator.remove();
  }
  return cacheKey;
}
