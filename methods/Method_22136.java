/** 
 * ??????????????.
 * @param from ??????
 * @param statisticInterval ??????
 * @return ????????????
 */
public List<TaskResultStatistics> findTaskResultStatistics(final Date from,final StatisticInterval statisticInterval){
  List<TaskResultStatistics> result=new LinkedList<>();
  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String sql=String.format("SELECT id, success_count, failed_count, statistics_time, creation_time FROM %s WHERE statistics_time >= '%s' order by id ASC",TABLE_TASK_RESULT_STATISTICS + "_" + statisticInterval,formatter.format(from));
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql);ResultSet resultSet=preparedStatement.executeQuery()){
    while (resultSet.next()) {
      TaskResultStatistics taskResultStatistics=new TaskResultStatistics(resultSet.getLong(1),resultSet.getInt(2),resultSet.getInt(3),statisticInterval,new Date(resultSet.getTimestamp(4).getTime()),new Date(resultSet.getTimestamp(5).getTime()));
      result.add(taskResultStatistics);
    }
  }
 catch (  final SQLException ex) {
    log.error("Fetch taskResultStatistics from DB error:",ex);
  }
  return result;
}
