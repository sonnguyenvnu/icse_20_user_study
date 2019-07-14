public long getDatabaseSize(){
  long size=0;
  if (cacheFile != null) {
    size+=cacheFile.length();
  }
  if (shmCacheFile != null) {
    size+=shmCacheFile.length();
  }
  return size;
}
