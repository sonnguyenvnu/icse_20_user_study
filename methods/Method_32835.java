public int removeTermSession(TerminalSession sessionToRemove){
  int indexOfRemoved=mTerminalSessions.indexOf(sessionToRemove);
  mTerminalSessions.remove(indexOfRemoved);
  if (mTerminalSessions.isEmpty() && mWakeLock == null) {
    stopSelf();
  }
 else {
    updateNotification();
  }
  return indexOfRemoved;
}
