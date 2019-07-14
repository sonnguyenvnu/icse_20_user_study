/** 
 * Marks the entry as orphan. 
 */
private synchronized void makeOrphan(Entry<K,V> entry){
  Preconditions.checkNotNull(entry);
  Preconditions.checkState(!entry.isOrphan);
  entry.isOrphan=true;
}
