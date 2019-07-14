/** 
 * ????????????.
 * @param jobRunningStatistics ????????????
 * @return ????????
 */
public boolean add(final JobRunningStatistics jobRunningStatistics){
  boolean result=false;
  String sql="INSERT INTO `" + TABLE_JOB_RUNNING_STATISTICS + "` (`running_count`, `statistics_time`, `creation_time`) VALUES (?, ?, ?);";
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql)){
    preparedStatement.setInt(1,jobRunningStatistics.getRunningCount());
    preparedStatement.setTimestamp(2,new Timestamp(jobRunningStatistics.getStatisticsTime().getTime()));
    preparedStatement.setTimestamp(3,new Timestamp(jobRunningStatistics.getCreationTime().getTime()));
    preparedStatement.execute();
    result=true;
  }
 catch (  final SQLException ex) {
    log.error("Insert jobRunningStatistics to DB error:",ex);
  }
  return result;
}
