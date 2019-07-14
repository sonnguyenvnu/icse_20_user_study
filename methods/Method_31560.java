/** 
 * Executes this query with these parameters against this connection.
 * @param query  The query to execute.
 * @param params The query parameters.
 * @return The query results as a list of strings.
 * @throws SQLException when the query execution failed.
 */
public List<String> queryForStringList(String query,String... params) throws SQLException {
  PreparedStatement statement=null;
  ResultSet resultSet=null;
  List<String> result;
  try {
    statement=prepareStatement(query,params);
    resultSet=statement.executeQuery();
    result=new ArrayList<>();
    while (resultSet.next()) {
      result.add(resultSet.getString(1));
    }
  }
  finally {
    JdbcUtils.closeResultSet(resultSet);
    JdbcUtils.closeStatement(statement);
  }
  return result;
}
