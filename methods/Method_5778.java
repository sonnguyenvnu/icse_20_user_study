/** 
 * Ensures that the cache's in-memory representation has been initialized. 
 */
private void initialize(){
  if (!cacheDir.exists()) {
    cacheDir.mkdirs();
    return;
  }
  index.load();
  loadDirectory(cacheDir,true);
  index.removeEmpty();
  try {
    index.store();
  }
 catch (  CacheException e) {
    Log.e(TAG,"Storing index file failed",e);
  }
}
