/** 
 * Called when the client closes its reference. 
 */
private void releaseClientReference(final Entry<K,V> entry){
  Preconditions.checkNotNull(entry);
  boolean isExclusiveAdded;
  CloseableReference<V> oldRefToClose;
synchronized (this) {
    decreaseClientCount(entry);
    isExclusiveAdded=maybeAddToExclusives(entry);
    oldRefToClose=referenceToClose(entry);
  }
  CloseableReference.closeSafely(oldRefToClose);
  maybeNotifyExclusiveEntryInsertion(isExclusiveAdded ? entry : null);
  maybeUpdateCacheParams();
  maybeEvictEntries();
}
