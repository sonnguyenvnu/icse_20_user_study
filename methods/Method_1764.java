/** 
 * If file cache size is not calculated or if it was calculated a long time ago (FILECACHE_SIZE_UPDATE_PERIOD_MS) recalculated from file listing.
 * @return true if it was recalculated, false otherwise.
 */
@GuardedBy("mLock") private boolean maybeUpdateFileCacheSize(){
  long now=mClock.now();
  if ((!mCacheStats.isInitialized()) || mCacheSizeLastUpdateTime == UNINITIALIZED || (now - mCacheSizeLastUpdateTime) > FILECACHE_SIZE_UPDATE_PERIOD_MS) {
    return maybeUpdateFileCacheSizeAndIndex();
  }
  return false;
}
