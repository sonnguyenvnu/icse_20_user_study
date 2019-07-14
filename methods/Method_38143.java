/** 
 * Executes UPDATE, INSERT or DELETE queries and optionally closes the query.
 * @see Statement#executeUpdate(String)
 */
protected int executeUpdate(final boolean closeQuery){
  start=System.currentTimeMillis();
  init();
  final int result;
  if (log.isDebugEnabled()) {
    log.debug("Executing update: " + getQueryString());
  }
  try {
    if (preparedStatement == null) {
      if (generatedColumns != null) {
        if (generatedColumns.length == 0) {
          result=statement.executeUpdate(query.sql,Statement.RETURN_GENERATED_KEYS);
        }
 else {
          result=statement.executeUpdate(query.sql,generatedColumns);
        }
      }
 else {
        result=statement.executeUpdate(query.sql);
      }
    }
 else {
      result=preparedStatement.executeUpdate();
    }
  }
 catch (  SQLException sex) {
    throw new DbSqlException(this,"Query execution failed",sex);
  }
  if (closeQuery) {
    close();
  }
  elapsed=System.currentTimeMillis() - start;
  if (log.isDebugEnabled()) {
    log.debug("execution time: " + elapsed + "ms");
  }
  return result;
}
