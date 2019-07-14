private String getOriginalTaskId(final String taskId){
  String sql=String.format("SELECT original_task_id FROM %s WHERE task_id = '%s' and state='%s' LIMIT 1",TABLE_JOB_STATUS_TRACE_LOG,taskId,State.TASK_STAGING);
  String result="";
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql);ResultSet resultSet=preparedStatement.executeQuery()){
    if (resultSet.next()) {
      return resultSet.getString("original_task_id");
    }
  }
 catch (  final SQLException ex) {
    log.error(ex.getMessage());
  }
  return result;
}
