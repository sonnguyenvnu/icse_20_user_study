@Override public void unlock(Session s){
  if (database != null) {
    traceLock(s,lockExclusiveSession == s,"unlock");
    if (lockExclusiveSession == s) {
      lockSharedSessions.remove(s);
      lockExclusiveSession=null;
    }
synchronized (database) {
      if (!lockSharedSessions.isEmpty()) {
        lockSharedSessions.remove(s);
      }
      if (!waitingSessions.isEmpty()) {
        database.notifyAll();
      }
    }
  }
}
