private int getEventCount(final String tableName,final Collection<String> tableFields,final Condition condition){
  int result=0;
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=createCountPreparedStatement(conn,tableName,tableFields,condition);ResultSet resultSet=preparedStatement.executeQuery()){
    resultSet.next();
    result=resultSet.getInt(1);
  }
 catch (  final SQLException ex) {
    log.error("Fetch EventCount from DB error:",ex);
  }
  return result;
}
