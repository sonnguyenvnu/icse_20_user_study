/** 
 * Executes this query with these parameters against this connection.
 * @param query  The query to execute.
 * @param params The query parameters.
 * @return The query result.
 * @throws SQLException when the query execution failed.
 */
public int queryForInt(String query,String... params) throws SQLException {
  PreparedStatement statement=null;
  ResultSet resultSet=null;
  int result;
  try {
    statement=prepareStatement(query,params);
    resultSet=statement.executeQuery();
    resultSet.next();
    result=resultSet.getInt(1);
  }
  finally {
    JdbcUtils.closeResultSet(resultSet);
    JdbcUtils.closeStatement(statement);
  }
  return result;
}
