@Override public boolean needCached(){
  if (data instanceof CacheItem) {
    return ((CacheItem)data).isStableCache();
  }
  return false;
}
