@Override public void rollback(Savepoint s) throws SQLServerException {
  loggerExternal.entering(getClassNameLogging(),"rollback",s);
  if (loggerExternal.isLoggable(Level.FINER) && Util.isActivityTraceOn()) {
    loggerExternal.finer(toString() + " ActivityId: " + ActivityCorrelator.getNext().toString());
  }
  checkClosed();
  if (databaseAutoCommitMode) {
    SQLServerException.makeFromDriverError(this,this,SQLServerException.getErrString("R_cantInvokeRollback"),null,false);
  }
  connectionCommand("IF @@TRANCOUNT > 0 ROLLBACK TRAN " + Util.escapeSQLId(((SQLServerSavepoint)s).getLabel()),"rollbackSavepoint");
  loggerExternal.exiting(getClassNameLogging(),"rollback");
}
