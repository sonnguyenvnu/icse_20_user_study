/** 
 * ??????????????.
 * @param from ??????
 * @return ????????????
 */
public List<TaskRunningStatistics> findTaskRunningStatistics(final Date from){
  List<TaskRunningStatistics> result=new LinkedList<>();
  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String sql=String.format("SELECT id, running_count, statistics_time, creation_time FROM %s WHERE statistics_time >= '%s' order by id ASC",TABLE_TASK_RUNNING_STATISTICS,formatter.format(from));
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql);ResultSet resultSet=preparedStatement.executeQuery()){
    while (resultSet.next()) {
      TaskRunningStatistics taskRunningStatistics=new TaskRunningStatistics(resultSet.getLong(1),resultSet.getInt(2),new Date(resultSet.getTimestamp(3).getTime()),new Date(resultSet.getTimestamp(4).getTime()));
      result.add(taskRunningStatistics);
    }
  }
 catch (  final SQLException ex) {
    log.error("Fetch taskRunningStatistics from DB error:",ex);
  }
  return result;
}
