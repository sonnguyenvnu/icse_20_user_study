/** 
 * Returns the value reference of the entry if it should be closed, null otherwise. 
 */
@Nullable private synchronized CloseableReference<V> referenceToClose(Entry<K,V> entry){
  Preconditions.checkNotNull(entry);
  return (entry.isOrphan && entry.clientCount == 0) ? entry.valueRef : null;
}
