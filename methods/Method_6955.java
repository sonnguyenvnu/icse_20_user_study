private void cleanupInternal(boolean deleteFiles){
  lastDateValue=0;
  lastSeqValue=0;
  lastPtsValue=0;
  lastQtsValue=0;
  lastSecretVersion=0;
  lastSavedSeq=0;
  lastSavedPts=0;
  lastSavedDate=0;
  lastSavedQts=0;
  secretPBytes=null;
  secretG=0;
  if (database != null) {
    database.close();
    database=null;
  }
  if (deleteFiles) {
    if (cacheFile != null) {
      cacheFile.delete();
      cacheFile=null;
    }
    if (walCacheFile != null) {
      walCacheFile.delete();
      walCacheFile=null;
    }
    if (shmCacheFile != null) {
      shmCacheFile.delete();
      shmCacheFile=null;
    }
  }
}
