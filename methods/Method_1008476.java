public void add(QueryCacheStats stats){
  ramBytesUsed+=stats.ramBytesUsed;
  hitCount+=stats.hitCount;
  missCount+=stats.missCount;
  cacheCount+=stats.cacheCount;
  cacheSize+=stats.cacheSize;
}
