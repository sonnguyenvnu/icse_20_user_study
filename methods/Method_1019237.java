/** 
 * Verify or refresh the state of the shared library store.
 */
@Override protected void prepare(int flags) throws IOException {
  SysUtil.mkdirOrThrow(soDirectory);
  File lockFileName=new File(soDirectory,LOCK_FILE_NAME);
  FileLocker lock=FileLocker.lock(lockFileName);
  try {
    Log.v(TAG,"locked dso store " + soDirectory);
    if (refreshLocked(lock,flags,getDepsBlock())) {
      lock=null;
    }
 else {
      Log.i(TAG,"dso store is up-to-date: " + soDirectory);
    }
  }
  finally {
    if (lock != null) {
      Log.v(TAG,"releasing dso store lock for " + soDirectory);
      lock.close();
    }
 else {
      Log.v(TAG,"not releasing dso store lock for " + soDirectory + " (syncer thread started)");
    }
  }
}
