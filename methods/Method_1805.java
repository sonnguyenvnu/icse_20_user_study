/** 
 * Decreases the entry's client count. 
 */
private synchronized void decreaseClientCount(Entry<K,V> entry){
  Preconditions.checkNotNull(entry);
  Preconditions.checkState(entry.clientCount > 0);
  entry.clientCount--;
}
