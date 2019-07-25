@Override public void remove(String cacheName,Object key){
  redis.del(buildKey(cacheName,key));
}
