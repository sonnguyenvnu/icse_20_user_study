private void initTables() throws SQLException {
  try (Connection conn=dataSource.getConnection()){
    createTaskResultTableIfNeeded(conn);
    createTaskRunningTableIfNeeded(conn);
    createJobRunningTableIfNeeded(conn);
    createJobRegisterTableIfNeeded(conn);
  }
 }
