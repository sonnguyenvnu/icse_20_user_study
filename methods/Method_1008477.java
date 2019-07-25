public void add(RequestCacheStats stats){
  this.memorySize+=stats.memorySize;
  this.evictions+=stats.evictions;
  this.hitCount+=stats.hitCount;
  this.missCount+=stats.missCount;
}
