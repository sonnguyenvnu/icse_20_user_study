/** 
 * Increases the entry's client count. 
 */
private synchronized void increaseClientCount(Entry<K,V> entry){
  Preconditions.checkNotNull(entry);
  Preconditions.checkState(!entry.isOrphan);
  entry.clientCount++;
}
