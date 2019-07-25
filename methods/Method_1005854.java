public boolean del(String key){
  getCache().invalidate(CACHE_PREFIX + key);
  return true;
}
