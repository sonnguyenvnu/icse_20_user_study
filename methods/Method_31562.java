/** 
 * Executes this query with these parameters against this connection.
 * @param query  The query to execute.
 * @param params The query parameters.
 * @return The query result.
 * @throws SQLException when the query execution failed.
 */
public String queryForString(String query,String... params) throws SQLException {
  PreparedStatement statement=null;
  ResultSet resultSet=null;
  String result;
  try {
    statement=prepareStatement(query,params);
    resultSet=statement.executeQuery();
    result=null;
    if (resultSet.next()) {
      result=resultSet.getString(1);
    }
  }
  finally {
    JdbcUtils.closeResultSet(resultSet);
    JdbcUtils.closeStatement(statement);
  }
  return result;
}
