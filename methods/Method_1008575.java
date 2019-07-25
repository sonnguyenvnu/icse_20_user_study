void clear(CacheEntity entity){
  keysToClean.add(new CleanupKey(entity,-1));
  cleanCache();
}
