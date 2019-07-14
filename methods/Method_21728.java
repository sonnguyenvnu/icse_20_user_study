/** 
 * Prepare action And transform sql into ES ActionRequest
 * @param sql SQL query to execute.
 * @return ES request
 * @throws SqlParseException
 */
public QueryAction explain(String sql) throws SqlParseException, SQLFeatureNotSupportedException {
  return ESActionFactory.create(client,sql);
}
