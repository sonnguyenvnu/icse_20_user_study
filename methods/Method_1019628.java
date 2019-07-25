public void dispose(){
  if (cacheEntries != null) {
    cacheEntries.clear();
  }
  cacheEntries=null;
  if (cacheEntriesInfo != null) {
    cacheEntriesInfo.clear();
  }
  cacheEntriesInfo=null;
  if (lastModifiedEntries != null) {
    lastModifiedEntries.clear();
  }
  lastModifiedEntries=null;
}
