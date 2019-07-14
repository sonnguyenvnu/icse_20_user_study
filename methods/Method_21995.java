private void createTaskIdAndStateIndex(final Connection conn,final String tableName) throws SQLException {
  String sql="CREATE INDEX " + TASK_ID_STATE_INDEX + " ON " + tableName + " (`task_id`, `state`);";
  try (PreparedStatement preparedStatement=conn.prepareStatement(sql)){
    preparedStatement.execute();
  }
 }
