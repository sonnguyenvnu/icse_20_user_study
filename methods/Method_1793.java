/** 
 * Gets the value with the given key to be reused, or null if there is no such value. <p> The item can be reused only if it is exclusively owned by the cache.
 */
@Nullable public CloseableReference<V> reuse(K key){
  Preconditions.checkNotNull(key);
  CloseableReference<V> clientRef=null;
  boolean removed=false;
  Entry<K,V> oldExclusive=null;
synchronized (this) {
    oldExclusive=mExclusiveEntries.remove(key);
    if (oldExclusive != null) {
      Entry<K,V> entry=mCachedEntries.remove(key);
      Preconditions.checkNotNull(entry);
      Preconditions.checkState(entry.clientCount == 0);
      clientRef=entry.valueRef;
      removed=true;
    }
  }
  if (removed) {
    maybeNotifyExclusiveEntryRemoval(oldExclusive);
  }
  return clientRef;
}
