private void createTaskResultTableIfNeeded(final Connection conn) throws SQLException {
  DatabaseMetaData dbMetaData=conn.getMetaData();
  for (  StatisticInterval each : StatisticInterval.values()) {
    try (ResultSet resultSet=dbMetaData.getTables(null,null,TABLE_TASK_RESULT_STATISTICS + "_" + each,new String[]{"TABLE"})){
      if (!resultSet.next()) {
        createTaskResultTable(conn,each);
      }
    }
   }
}
