@Override public void unlock(Session s){
  if (database != null) {
    boolean wasLocked=lockExclusiveSession == s;
    traceLock(s,wasLocked,TraceLockEvent.TRACE_LOCK_UNLOCK,NO_EXTRA_INFO);
    if (wasLocked) {
      lockSharedSessions.remove(s);
      lockExclusiveSession=null;
      if (SysProperties.THREAD_DEADLOCK_DETECTOR) {
        if (EXCLUSIVE_LOCKS.get() != null) {
          EXCLUSIVE_LOCKS.get().remove(getName());
        }
      }
    }
 else {
      wasLocked=lockSharedSessions.remove(s) != null;
      if (SysProperties.THREAD_DEADLOCK_DETECTOR) {
        if (SHARED_LOCKS.get() != null) {
          SHARED_LOCKS.get().remove(getName());
        }
      }
    }
    if (wasLocked && !waitingSessions.isEmpty()) {
      Object lockSyncObject=getLockSyncObject();
synchronized (lockSyncObject) {
        lockSyncObject.notifyAll();
      }
    }
  }
}
