private void createJobStatusTraceTableAndIndexIfNeeded(final Connection conn) throws SQLException {
  DatabaseMetaData dbMetaData=conn.getMetaData();
  try (ResultSet resultSet=dbMetaData.getTables(null,null,TABLE_JOB_STATUS_TRACE_LOG,new String[]{"TABLE"})){
    if (!resultSet.next()) {
      createJobStatusTraceTable(conn);
    }
  }
   createTaskIdIndexIfNeeded(conn,TABLE_JOB_STATUS_TRACE_LOG,TASK_ID_STATE_INDEX);
}
