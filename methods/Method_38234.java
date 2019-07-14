/** 
 * Executes the query and returns  {@link #createResultSetMapper(java.sql.ResultSet) builded ResultSet mapper}.
 */
protected ResultSetMapper executeAndBuildResultSetMapper(){
  ResultSet resultSet=execute();
  return createResultSetMapper(resultSet);
}
