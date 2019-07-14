private PduCacheEntry purgeSingleEntry(Uri key){
  mUpdating.remove(key);
  PduCacheEntry entry=super.purge(key);
  if (entry != null) {
    removeFromThreads(key,entry);
    removeFromMessageBoxes(key,entry);
    return entry;
  }
  return null;
}
