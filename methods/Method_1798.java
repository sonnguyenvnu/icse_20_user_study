/** 
 * Notifies the client that the cache no longer tracks the given items. <p> This method invokes the external  {@link CloseableReference#close} method,so it must not be called while holding the <code>this</code> lock.
 */
private void maybeClose(@Nullable ArrayList<Entry<K,V>> oldEntries){
  if (oldEntries != null) {
    for (    Entry<K,V> oldEntry : oldEntries) {
      CloseableReference.closeSafely(referenceToClose(oldEntry));
    }
  }
}
