private void createJobRegisterTableIfNeeded(final Connection conn) throws SQLException {
  DatabaseMetaData dbMetaData=conn.getMetaData();
  try (ResultSet resultSet=dbMetaData.getTables(null,null,TABLE_JOB_REGISTER_STATISTICS,new String[]{"TABLE"})){
    if (!resultSet.next()) {
      createJobRegisterTable(conn);
    }
  }
 }
