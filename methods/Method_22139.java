/** 
 * ????????????????.
 * @return ????????????
 */
public Optional<TaskRunningStatistics> findLatestTaskRunningStatistics(){
  TaskRunningStatistics result=null;
  String sql=String.format("SELECT id, running_count, statistics_time, creation_time FROM %s order by id DESC LIMIT 1",TABLE_TASK_RUNNING_STATISTICS);
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql);ResultSet resultSet=preparedStatement.executeQuery()){
    while (resultSet.next()) {
      result=new TaskRunningStatistics(resultSet.getLong(1),resultSet.getInt(2),new Date(resultSet.getTimestamp(3).getTime()),new Date(resultSet.getTimestamp(4).getTime()));
    }
  }
 catch (  final SQLException ex) {
    log.error("Fetch latest taskRunningStatistics from DB error:",ex);
  }
  return Optional.fromNullable(result);
}
