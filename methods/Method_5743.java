/** 
 * Stores the index data to index file if there is a change. 
 */
public void store() throws CacheException {
  if (!changed) {
    return;
  }
  writeFile();
  changed=false;
  int removedIdCount=removedIds.size();
  for (int i=0; i < removedIdCount; i++) {
    idToKey.remove(removedIds.keyAt(i));
  }
  removedIds.clear();
}
