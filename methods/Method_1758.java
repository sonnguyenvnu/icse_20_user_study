@Override public BinaryResource insert(CacheKey key,WriterCallback callback) throws IOException {
  SettableCacheEvent cacheEvent=SettableCacheEvent.obtain().setCacheKey(key);
  mCacheEventListener.onWriteAttempt(cacheEvent);
  String resourceId;
synchronized (mLock) {
    resourceId=CacheKeyUtil.getFirstResourceId(key);
  }
  cacheEvent.setResourceId(resourceId);
  try {
    DiskStorage.Inserter inserter=startInsert(resourceId,key);
    try {
      inserter.writeData(callback,key);
      BinaryResource resource=endInsert(inserter,key,resourceId);
      cacheEvent.setItemSize(resource.size()).setCacheSize(mCacheStats.getSize());
      mCacheEventListener.onWriteSuccess(cacheEvent);
      return resource;
    }
  finally {
      if (!inserter.cleanUp()) {
        FLog.e(TAG,"Failed to delete temp file");
      }
    }
  }
 catch (  IOException ioe) {
    cacheEvent.setException(ioe);
    mCacheEventListener.onWriteException(cacheEvent);
    FLog.e(TAG,"Failed inserting a file into the cache",ioe);
    throw ioe;
  }
 finally {
    cacheEvent.recycle();
  }
}
