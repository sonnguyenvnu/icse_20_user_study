/** 
 * Marks the given entries as orphans. 
 */
private synchronized void makeOrphans(@Nullable ArrayList<Entry<K,V>> oldEntries){
  if (oldEntries != null) {
    for (    Entry<K,V> oldEntry : oldEntries) {
      makeOrphan(oldEntry);
    }
  }
}
