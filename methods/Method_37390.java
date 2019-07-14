/** 
 * Prune only expired objects, <code>LinkedHashMap</code> will take care of LRU if needed.
 */
@Override protected int pruneCache(){
  if (!isPruneExpiredActive()) {
    return 0;
  }
  int count=0;
  Iterator<CacheObject<K,V>> values=cacheMap.values().iterator();
  while (values.hasNext()) {
    CacheObject<K,V> co=values.next();
    if (co.isExpired()) {
      values.remove();
      onRemove(co.key,co.cachedObject);
      count++;
    }
  }
  return count;
}
