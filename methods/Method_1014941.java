@Override public void remove(String cacheName,Object key){
  Cache cache=Redis.use(cacheName);
  if (cache == null) {
    return;
  }
  cache.del(key);
}
