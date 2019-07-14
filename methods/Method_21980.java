private PreparedStatement createDataPreparedStatement(final Connection conn,final String tableName,final Collection<String> tableFields,final Condition condition) throws SQLException {
  String sql=buildDataSql(tableName,tableFields,condition);
  PreparedStatement preparedStatement=conn.prepareStatement(sql);
  setBindValue(preparedStatement,tableFields,condition);
  return preparedStatement;
}
