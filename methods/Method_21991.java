private void initTablesAndIndexes() throws SQLException {
  try (Connection conn=dataSource.getConnection()){
    createJobExecutionTableAndIndexIfNeeded(conn);
    createJobStatusTraceTableAndIndexIfNeeded(conn);
    databaseType=DatabaseType.valueFrom(conn.getMetaData().getDatabaseProductName());
  }
 }
