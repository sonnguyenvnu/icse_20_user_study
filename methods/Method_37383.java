/** 
 * Prune expired objects and, if cache is still full, the first one.
 */
@Override protected int pruneCache(){
  int count=0;
  CacheObject<K,V> first=null;
  Iterator<CacheObject<K,V>> values=cacheMap.values().iterator();
  while (values.hasNext()) {
    CacheObject<K,V> co=values.next();
    if (co.isExpired()) {
      values.remove();
      onRemove(co.key,co.cachedObject);
      count++;
    }
    if (first == null) {
      first=co;
    }
  }
  if (isFull()) {
    if (first != null) {
      cacheMap.remove(first.key);
      onRemove(first.key,first.cachedObject);
      count++;
    }
  }
  return count;
}
