@Override public void trimToMinimum(){
synchronized (mLock) {
    maybeUpdateFileCacheSize();
    long cacheSize=mCacheStats.getSize();
    if (mCacheSizeLimitMinimum <= 0 || cacheSize <= 0 || cacheSize < mCacheSizeLimitMinimum) {
      return;
    }
    double trimRatio=1 - (double)mCacheSizeLimitMinimum / (double)cacheSize;
    if (trimRatio > TRIMMING_LOWER_BOUND) {
      trimBy(trimRatio);
    }
  }
}
