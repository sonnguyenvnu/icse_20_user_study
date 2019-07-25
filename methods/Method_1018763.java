@Override public void commit() throws SQLServerException {
  loggerExternal.entering(getClassNameLogging(),"commit");
  if (loggerExternal.isLoggable(Level.FINER) && Util.isActivityTraceOn()) {
    loggerExternal.finer(toString() + " ActivityId: " + ActivityCorrelator.getNext().toString());
  }
  checkClosed();
  if (!databaseAutoCommitMode)   connectionCommand("IF @@TRANCOUNT > 0 COMMIT TRAN","Connection.commit");
  loggerExternal.exiting(getClassNameLogging(),"commit");
}
