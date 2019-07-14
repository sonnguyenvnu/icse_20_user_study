private void acquireGlobalXLock(){
  LockValue lockValue=new LockValue();
  lockValue.setLockType(DTXLocks.X_LOCK);
  while (true) {
    try {
      acquireLocks(GLOBAL_CONTEXT,Sets.newHashSet(GLOBAL_LOCK_ID),lockValue);
      break;
    }
 catch (    FastStorageException ignored) {
    }
  }
}
