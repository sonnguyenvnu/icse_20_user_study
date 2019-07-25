@Override public CachedQuery create(Object key) throws SQLException {
  assert key instanceof String || key instanceof BaseQueryKey : "Query key should be String or BaseQueryKey. Given " + key.getClass() + ", sql: " + String.valueOf(key);
  BaseQueryKey queryKey;
  String parsedSql;
  if (key instanceof BaseQueryKey) {
    queryKey=(BaseQueryKey)key;
    parsedSql=queryKey.sql;
  }
 else {
    queryKey=null;
    parsedSql=(String)key;
  }
  if (key instanceof String || queryKey.escapeProcessing) {
    parsedSql=Parser.replaceProcessing(parsedSql,true,queryExecutor.getStandardConformingStrings());
  }
  boolean isFunction;
  if (key instanceof CallableQueryKey) {
    JdbcCallParseInfo callInfo=Parser.modifyJdbcCall(parsedSql,queryExecutor.getStandardConformingStrings(),queryExecutor.getServerVersionNum(),queryExecutor.getProtocolVersion());
    parsedSql=callInfo.getSql();
    isFunction=callInfo.isFunction();
  }
 else {
    isFunction=false;
  }
  boolean isParameterized=key instanceof String || queryKey.isParameterized;
  boolean splitStatements=isParameterized || queryExecutor.getPreferQueryMode().compareTo(PreferQueryMode.EXTENDED) >= 0;
  String[] returningColumns;
  if (key instanceof QueryWithReturningColumnsKey) {
    returningColumns=((QueryWithReturningColumnsKey)key).columnNames;
  }
 else {
    returningColumns=EMPTY_RETURNING;
  }
  List<NativeQuery> queries=Parser.parseJdbcSql(parsedSql,queryExecutor.getStandardConformingStrings(),isParameterized,splitStatements,queryExecutor.isReWriteBatchedInsertsEnabled(),returningColumns);
  Query query=queryExecutor.wrap(queries);
  return new CachedQuery(key,query,isFunction);
}
