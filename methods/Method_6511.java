public boolean isInCache(String key){
  return memCache.get(key) != null;
}
