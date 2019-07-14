public Map<Long,V> getLatestAccessedItems(int n){
  final Map<Long,V> result=new LinkedHashMap<>();
  if (n <= 0)   return result;
  final TreeSet<CacheEntry<Long,V>> tree=new TreeSet<>();
  markAndSweepLock.lock();
  try {
    for (    Map.Entry<Long,CacheEntry<Long,V>> entry : map.entrySet()) {
      final CacheEntry<Long,V> ce=entry.getValue();
      ce.lastAccessedCopy=ce.lastAccessed;
      if (tree.size() < n) {
        tree.add(ce);
      }
 else {
        if (ce.lastAccessedCopy > tree.last().lastAccessedCopy) {
          tree.remove(tree.last());
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
