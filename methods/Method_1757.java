/** 
 * Probes whether the object corresponding to the mKey is in the cache. Note that the act of probing touches the item (if present in cache), thus changing its LRU timestamp. <p> This will be faster than retrieving the object, but it still has file system accesses and should NOT be called on the UI thread.
 * @param key the mKey to check
 * @return whether the keyed mValue is in the cache
 */
public boolean probe(final CacheKey key){
  String resourceId=null;
  try {
synchronized (mLock) {
      List<String> resourceIds=CacheKeyUtil.getResourceIds(key);
      for (int i=0; i < resourceIds.size(); i++) {
        resourceId=resourceIds.get(i);
        if (mStorage.touch(resourceId,key)) {
          mResourceIndex.add(resourceId);
          return true;
        }
      }
      return false;
    }
  }
 catch (  IOException e) {
    SettableCacheEvent cacheEvent=SettableCacheEvent.obtain().setCacheKey(key).setResourceId(resourceId).setException(e);
    mCacheEventListener.onReadException(cacheEvent);
    cacheEvent.recycle();
    return false;
  }
}
