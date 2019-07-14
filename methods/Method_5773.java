@Override public synchronized void release(){
  if (released) {
    return;
  }
  listeners.clear();
  removeStaleSpans();
  try {
    index.store();
  }
 catch (  CacheException e) {
    Log.e(TAG,"Storing index file failed",e);
  }
 finally {
    unlockFolder(cacheDir);
    released=true;
  }
}
