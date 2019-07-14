protected boolean isReallyFull(final K key){
  if (cacheSize == 0) {
    return false;
  }
  if (cacheMap.size() >= cacheSize) {
    return !cacheMap.containsKey(key);
  }
 else {
    return false;
  }
}
