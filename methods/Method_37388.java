/** 
 * Prunes expired and, if cache is still full, the LFU element(s) from the cache. On LFU removal, access count is normalized to value which had removed object. Returns the number of removed objects.
 */
@Override protected int pruneCache(){
  int count=0;
  CacheObject<K,V> comin=null;
  Iterator<CacheObject<K,V>> values=cacheMap.values().iterator();
  while (values.hasNext()) {
    CacheObject<K,V> co=values.next();
    if (co.isExpired()) {
      values.remove();
      onRemove(co.key,co.cachedObject);
      count++;
      continue;
    }
    if (comin == null) {
      comin=co;
    }
 else {
      if (co.accessCount < comin.accessCount) {
        comin=co;
      }
    }
  }
  if (!isFull()) {
    return count;
  }
  if (comin != null) {
    long minAccessCount=comin.accessCount;
    values=cacheMap.values().iterator();
    while (values.hasNext()) {
      CacheObject<K,V> co=values.next();
      co.accessCount-=minAccessCount;
      if (co.accessCount <= 0) {
        values.remove();
        onRemove(co.key,co.cachedObject);
        count++;
      }
    }
  }
  return count;
}
