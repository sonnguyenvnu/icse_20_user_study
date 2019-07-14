@Override public void onSpanAdded(Cache cache,CacheSpan span){
  leastRecentlyUsed.add(span);
  currentSize+=span.length;
  evictCache(cache,0);
}
