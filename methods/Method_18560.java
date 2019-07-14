private Object getCreateInitialStateLock(String globalKey,Object lock){
synchronized (this) {
    if (mCreateInitialStateLock == null) {
      mCreateInitialStateLock=new HashMap<>();
    }
  }
synchronized (mCreateInitialStateLock) {
    Object existingLock=mCreateInitialStateLock.get(globalKey);
    if (existingLock != null) {
      return existingLock;
    }
    mCreateInitialStateLock.put(globalKey,lock);
  }
  return lock;
}
