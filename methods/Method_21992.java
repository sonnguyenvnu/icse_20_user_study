private void createJobExecutionTableAndIndexIfNeeded(final Connection conn) throws SQLException {
  DatabaseMetaData dbMetaData=conn.getMetaData();
  try (ResultSet resultSet=dbMetaData.getTables(null,null,TABLE_JOB_EXECUTION_LOG,new String[]{"TABLE"})){
    if (!resultSet.next()) {
      createJobExecutionTable(conn);
    }
  }
 }
