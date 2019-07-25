/** 
 * Flush the current value, including the margin, to disk.
 * @param session the session
 */
public void flush(Session session){
  if (isTemporary()) {
    return;
  }
  if (session == null || !database.isSysTableLockedBy(session)) {
    Session sysSession=database.getSystemSession();
synchronized (database.isMultiThreaded() ? sysSession : database) {
      flushInternal(sysSession);
      sysSession.commit(false);
    }
  }
 else {
synchronized (database.isMultiThreaded() ? session : database) {
      flushInternal(session);
    }
  }
}
