public void dispose(){
  if (cacheEntries != null) {
    cacheEntries.clear();
  }
  cacheEntries=null;
  if (lastModifiedEntries != null) {
    lastModifiedEntries.clear();
  }
  lastModifiedEntries=null;
}
