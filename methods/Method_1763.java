private void trimBy(final double trimRatio){
synchronized (mLock) {
    try {
      mCacheStats.reset();
      maybeUpdateFileCacheSize();
      long cacheSize=mCacheStats.getSize();
      long newMaxBytesInFiles=cacheSize - (long)(trimRatio * cacheSize);
      evictAboveSize(newMaxBytesInFiles,CacheEventListener.EvictionReason.CACHE_MANAGER_TRIMMED);
    }
 catch (    IOException ioe) {
      mCacheErrorLogger.logError(CacheErrorLogger.CacheErrorCategory.EVICTION,TAG,"trimBy: " + ioe.getMessage(),ioe);
    }
  }
}
