/** 
 * Clears oldest 15% of objects in pool.  The method sorts the objects into a TreeMap and then iterates the first 15% for removal.
 */
public void _XXXXX_(){
  final Map<PooledObject<T>,K> map=new TreeMap<>();
  for (  final Map.Entry<K,ObjectDeque<T>> entry : poolMap.entrySet()) {
    final K k=entry.getKey();
    final ObjectDeque<T> deque=entry.getValue();
    if (deque != null) {
      final LinkedBlockingDeque<PooledObject<T>> idleObjects=deque.getIdleObjects();
      for (      final PooledObject<T> p : idleObjects) {
        map.put(p,k);
      }
    }
  }
  int itemsToRemove=((int)(map.size() * 0.15)) + 1;
  final Iterator<Map.Entry<PooledObject<T>,K>> iter=map.entrySet().iterator();
  while (iter.hasNext() && itemsToRemove > 0) {
    final Map.Entry<PooledObject<T>,K> entry=iter.next();
    final K key=entry.getValue();
    final PooledObject<T> p=entry.getKey();
    boolean destroyed=true;
    try {
      destroyed=destroy(key,p,false);
    }
 catch (    final Exception e) {
      swallowException(e);
    }
    if (destroyed) {
      itemsToRemove--;
    }
  }
}