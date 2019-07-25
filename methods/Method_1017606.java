public synchronized void execute(Query[] queries,ParameterList[] parameterLists,BatchResultHandler batchHandler,int maxRows,int fetchSize,int flags) throws SQLException {
  waitOnLock();
  if (LOGGER.isLoggable(Level.FINEST)) {
    LOGGER.log(Level.FINEST,"  batch execute {0} queries, handler={1}, maxRows={2}, fetchSize={3}, flags={4}",new Object[]{queries.length,batchHandler,maxRows,fetchSize,flags});
  }
  flags=updateQueryMode(flags);
  boolean describeOnly=(QUERY_DESCRIBE_ONLY & flags) != 0;
  if (!describeOnly) {
    for (    ParameterList parameterList : parameterLists) {
      if (parameterList != null) {
        ((V3ParameterList)parameterList).checkAllParametersSet();
      }
    }
  }
  boolean autosave=false;
  ResultHandler handler=batchHandler;
  try {
    handler=sendQueryPreamble(batchHandler,flags);
    autosave=sendAutomaticSavepoint(queries[0],flags);
    estimatedReceiveBufferBytes=0;
    for (int i=0; i < queries.length; ++i) {
      Query query=queries[i];
      V3ParameterList parameters=(V3ParameterList)parameterLists[i];
      if (parameters == null) {
        parameters=SimpleQuery.NO_PARAMETERS;
      }
      sendQuery(query,parameters,maxRows,fetchSize,flags,handler,batchHandler);
      if (handler.getException() != null) {
        break;
      }
    }
    if (handler.getException() == null) {
      if ((flags & QueryExecutor.QUERY_EXECUTE_AS_SIMPLE) != 0) {
      }
 else {
        sendSync();
      }
      processResults(handler,flags);
      estimatedReceiveBufferBytes=0;
    }
  }
 catch (  IOException e) {
    abort();
    handler.handleError(new PSQLException(GT.tr("An I/O error occurred while sending to the backend."),PSQLState.CONNECTION_FAILURE,e));
  }
  try {
    handler.handleCompletion();
  }
 catch (  SQLException e) {
    rollbackIfRequired(autosave,e);
  }
}
