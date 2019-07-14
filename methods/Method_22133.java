/** 
 * ????????????.
 * @param taskRunningStatistics ????????????
 * @return ????????
 */
public boolean add(final TaskRunningStatistics taskRunningStatistics){
  boolean result=false;
  String sql="INSERT INTO `" + TABLE_TASK_RUNNING_STATISTICS + "` (`running_count`, `statistics_time`, `creation_time`) VALUES (?, ?, ?);";
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql)){
    preparedStatement.setInt(1,taskRunningStatistics.getRunningCount());
    preparedStatement.setTimestamp(2,new Timestamp(taskRunningStatistics.getStatisticsTime().getTime()));
    preparedStatement.setTimestamp(3,new Timestamp(taskRunningStatistics.getCreationTime().getTime()));
    preparedStatement.execute();
    result=true;
  }
 catch (  final SQLException ex) {
    log.error("Insert taskRunningStatistics to DB error:",ex);
  }
  return result;
}
