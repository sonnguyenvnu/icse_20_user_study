/** 
 * Executes this query with these parameters against this connection.
 * @param query  The query to execute.
 * @param params The query parameters.
 * @return The query results.
 * @throws SQLException when the query execution failed.
 */
public List<Map<String,String>> queryForList(String query,Object... params) throws SQLException {
  PreparedStatement statement=null;
  ResultSet resultSet=null;
  List<Map<String,String>> result;
  try {
    statement=prepareStatement(query,params);
    resultSet=statement.executeQuery();
    result=new ArrayList<>();
    while (resultSet.next()) {
      Map<String,String> rowMap=new LinkedHashMap<>();
      for (int i=1; i <= resultSet.getMetaData().getColumnCount(); i++) {
        rowMap.put(resultSet.getMetaData().getColumnLabel(i),resultSet.getString(i));
      }
      result.add(rowMap);
    }
  }
  finally {
    JdbcUtils.closeResultSet(resultSet);
    JdbcUtils.closeStatement(statement);
  }
  return result;
}
