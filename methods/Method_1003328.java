@Override public void put(CacheObject r){
  baseCache.put(r);
  map.put(r.getPos(),r);
}
