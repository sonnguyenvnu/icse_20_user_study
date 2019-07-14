/** 
 * Deletes old cache files.
 * @param cacheExpirationMs files older than this will be deleted.
 * @return the age in ms of the oldest file remaining in the cache.
 */
@Override public long clearOldEntries(long cacheExpirationMs){
  long oldestRemainingEntryAgeMs=0L;
synchronized (mLock) {
    try {
      long now=mClock.now();
      Collection<DiskStorage.Entry> allEntries=mStorage.getEntries();
      final long cacheSizeBeforeClearance=mCacheStats.getSize();
      int itemsRemovedCount=0;
      long itemsRemovedSize=0L;
      for (      DiskStorage.Entry entry : allEntries) {
        long entryAgeMs=Math.max(1,Math.abs(now - entry.getTimestamp()));
        if (entryAgeMs >= cacheExpirationMs) {
          long entryRemovedSize=mStorage.remove(entry);
          mResourceIndex.remove(entry.getId());
          if (entryRemovedSize > 0) {
            itemsRemovedCount++;
            itemsRemovedSize+=entryRemovedSize;
            SettableCacheEvent cacheEvent=SettableCacheEvent.obtain().setResourceId(entry.getId()).setEvictionReason(CacheEventListener.EvictionReason.CONTENT_STALE).setItemSize(entryRemovedSize).setCacheSize(cacheSizeBeforeClearance - itemsRemovedSize);
            mCacheEventListener.onEviction(cacheEvent);
            cacheEvent.recycle();
          }
        }
 else {
          oldestRemainingEntryAgeMs=Math.max(oldestRemainingEntryAgeMs,entryAgeMs);
        }
      }
      mStorage.purgeUnexpectedResources();
      if (itemsRemovedCount > 0) {
        maybeUpdateFileCacheSize();
        mCacheStats.increment(-itemsRemovedSize,-itemsRemovedCount);
      }
    }
 catch (    IOException ioe) {
      mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION,TAG,"clearOldEntries: " + ioe.getMessage(),ioe);
    }
  }
  return oldestRemainingEntryAgeMs;
}
