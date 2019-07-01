/** 
 * Calls query after checking the parameters to ensure nothing is null.
 * @param conn The connection to use for the query call.
 * @param closeConn True if the connection should be closed, false otherwise.
 * @param sql The SQL statement to execute.
 * @param params An array of query replacement parameters.  Each row inthis array is one set of batch replacement values.
 * @return The results of the query.
 * @throws SQLException If there are database or parameter errors.
 */
private <T>T _XXXXX_(final Connection conn,final boolean closeConn,final String sql,final ResultSetHandler<T> rsh,final Object... params) throws SQLException {
  if (conn == null) {
    throw new SQLException("Null connection");
  }
  if (sql == null) {
    if (closeConn) {
      close(conn);
    }
    throw new SQLException("Null SQL statement");
  }
  if (rsh == null) {
    if (closeConn) {
      close(conn);
    }
    throw new SQLException("Null ResultSetHandler");
  }
  PreparedStatement stmt=null;
  ResultSet rs=null;
  T result=null;
  try {
    stmt=this.prepareStatement(conn,sql);
    this.fillStatement(stmt,params);
    rs=this.wrap(stmt.executeQuery());
    result=rsh.handle(rs);
  }
 catch (  final SQLException e) {
    this.rethrow(e,sql,params);
  }
 finally {
    closeQuietly(rs);
    closeQuietly(stmt);
    if (closeConn) {
      close(conn);
    }
  }
  return result;
}