@Override public void rollback() throws SQLServerException {
  loggerExternal.entering(getClassNameLogging(),"rollback");
  if (loggerExternal.isLoggable(Level.FINER) && Util.isActivityTraceOn()) {
    loggerExternal.finer(toString() + " ActivityId: " + ActivityCorrelator.getNext().toString());
  }
  checkClosed();
  if (databaseAutoCommitMode) {
    SQLServerException.makeFromDriverError(this,this,SQLServerException.getErrString("R_cantInvokeRollback"),null,true);
  }
 else   connectionCommand("IF @@TRANCOUNT > 0 ROLLBACK TRAN","Connection.rollback");
  loggerExternal.exiting(getClassNameLogging(),"rollback");
}
