public static byte[][] run(QueryExecutor executor,String queryString,boolean wantResults) throws SQLException {
  Query query=executor.createSimpleQuery(queryString);
  SimpleResultHandler handler=new SimpleResultHandler();
  int flags=QueryExecutor.QUERY_ONESHOT | QueryExecutor.QUERY_SUPPRESS_BEGIN | QueryExecutor.QUERY_EXECUTE_AS_SIMPLE;
  if (!wantResults) {
    flags|=QueryExecutor.QUERY_NO_RESULTS | QueryExecutor.QUERY_NO_METADATA;
  }
  try {
    executor.execute(query,null,handler,0,0,flags);
  }
  finally {
    query.close();
  }
  if (!wantResults) {
    return null;
  }
  List<byte[][]> tuples=handler.getResults();
  if (tuples == null || tuples.size() != 1) {
    throw new PSQLException(GT.tr("An unexpected result was returned by a query."),PSQLState.CONNECTION_UNABLE_TO_CONNECT);
  }
  return tuples.get(0);
}
