protected void logStatementExecution(SqlStatement sqlStatement){
  if (LOG.isDebugEnabled()) {
    LOG.debug("Executing " + "SQL: " + sqlStatement.getSql());
  }
}
