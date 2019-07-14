private void createTaskRunningTableIfNeeded(final Connection conn) throws SQLException {
  DatabaseMetaData dbMetaData=conn.getMetaData();
  try (ResultSet resultSet=dbMetaData.getTables(null,null,TABLE_TASK_RUNNING_STATISTICS,new String[]{"TABLE"})){
    if (!resultSet.next()) {
      createTaskRunningTable(conn);
    }
  }
 }
