/** 
 * Backing  {@link ChronicleMap#put(Object,Object)} method.
 * @implNote the default implementation is equivalent to <pre>{@code // We cannot read the previous value under read lock, because then we will need // to release the read lock -> acquire write lock, the value might be updated in // between, that will break ConcurrentMap.put() atomicity guarantee. So, we acquire // update lock from the start: q.updateLock().lock(); MapEntry<K, V> entry = q.entry();}if (entry != null)  returnValue.returnValue(entry.value()); q.replaceValue(entry, value); } else { q.insert(q.absentEntry(), value); }}</pre>
 */
default void put(MapQueryContext<K,V,R> q,Data<V> value,ReturnValue<V> returnValue){
  q.updateLock().lock();
  MapEntry<K,V> entry=q.entry();
  if (entry != null) {
    returnValue.returnValue(entry.value());
    q.replaceValue(entry,value);
  }
 else {
    q.insert(q.absentEntry(),value);
  }
}
