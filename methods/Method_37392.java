/** 
 * Prunes expired elements from the cache. Returns the number of removed objects.
 */
@Override protected int pruneCache(){
  int count=0;
  Iterator<CacheObject<K,V>> values=cacheMap.values().iterator();
  while (values.hasNext()) {
    CacheObject co=values.next();
    if (co.isExpired()) {
      values.remove();
      count++;
    }
  }
  return count;
}
