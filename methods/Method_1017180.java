@Override public boolean acquire(K key,final Runnable cacheHit){
  if (cache.putIfAbsent(key,true) != null) {
    cacheHit.run();
    return false;
  }
  return true;
}
