/** 
 * Initialization code that can sometimes take a long time.
 */
private void ensureInitialized(){
  if (!mInitialized) {
    lock.lock();
    try {
      if (!mInitialized) {
        mInternalPath=Environment.getDataDirectory();
        mExternalPath=Environment.getExternalStorageDirectory();
        updateStats();
        mInitialized=true;
      }
    }
  finally {
      lock.unlock();
    }
  }
}
