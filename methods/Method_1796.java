/** 
 * Removes the exclusively owned items until the cache constraints are met. <p> This method invokes the external  {@link CloseableReference#close} method,so it must not be called while holding the <code>this</code> lock.
 */
private void maybeEvictEntries(){
  ArrayList<Entry<K,V>> oldEntries;
synchronized (this) {
    int maxCount=Math.min(mMemoryCacheParams.maxEvictionQueueEntries,mMemoryCacheParams.maxCacheEntries - getInUseCount());
    int maxSize=Math.min(mMemoryCacheParams.maxEvictionQueueSize,mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes());
    oldEntries=trimExclusivelyOwnedEntries(maxCount,maxSize);
    makeOrphans(oldEntries);
  }
  maybeClose(oldEntries);
  maybeNotifyExclusiveEntryRemoval(oldEntries);
}
