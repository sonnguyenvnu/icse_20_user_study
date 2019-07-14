/** 
 * Returns 'n' number of oldest accessed entries present in this cache. <p> This uses a TreeSet to collect the 'n' oldest items ordered by ascending last access time and returns a LinkedHashMap containing 'n' or less than 'n' entries.
 * @param n the number of oldest items needed
 * @return a LinkedHashMap containing 'n' or less than 'n' entries
 */
public Map<Long,V> getOldestAccessedItems(int n){
  final Map<Long,V> result=new LinkedHashMap<>();
  if (n <= 0)   return result;
  final TreeSet<CacheEntry<Long,V>> tree=new TreeSet<>();
  markAndSweepLock.lock();
  try {
    for (    Map.Entry<Long,CacheEntry<Long,V>> entry : map.entrySet()) {
      CacheEntry<Long,V> ce=entry.getValue();
      ce.lastAccessedCopy=ce.lastAccessed;
      if (tree.size() < n) {
        tree.add(ce);
      }
 else {
        if (ce.lastAccessedCopy < tree.first().lastAccessedCopy) {
          tree.remove(tree.first());
          tree.add(ce);
        }
      }
    }
  }
  finally {
    markAndSweepLock.unlock();
  }
  for (  CacheEntry<Long,V> e : tree) {
    result.put(e.key,e.value);
  }
  return result;
}
