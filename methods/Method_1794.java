/** 
 * Removes all the items from the cache whose key matches the specified predicate.
 * @param predicate returns true if an item with the given key should be removed
 * @return number of the items removed from the cache
 */
public int removeAll(Predicate<K> predicate){
  ArrayList<Entry<K,V>> oldExclusives;
  ArrayList<Entry<K,V>> oldEntries;
synchronized (this) {
    oldExclusives=mExclusiveEntries.removeAll(predicate);
    oldEntries=mCachedEntries.removeAll(predicate);
    makeOrphans(oldEntries);
  }
  maybeClose(oldEntries);
  maybeNotifyExclusiveEntryRemoval(oldExclusives);
  maybeUpdateCacheParams();
  maybeEvictEntries();
  return oldEntries.size();
}
