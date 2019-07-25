private boolean lock(K cached){
  return transitionalCache.putIfAbsent(cached,cached) == null;
}
