/** 
 * ??????????.
 * @param jobRegisterStatistics ??????????
 * @return ????????
 */
public boolean add(final JobRegisterStatistics jobRegisterStatistics){
  boolean result=false;
  String sql="INSERT INTO `" + TABLE_JOB_REGISTER_STATISTICS + "` (`registered_count`, `statistics_time`, `creation_time`) VALUES (?, ?, ?);";
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql)){
    preparedStatement.setInt(1,jobRegisterStatistics.getRegisteredCount());
    preparedStatement.setTimestamp(2,new Timestamp(jobRegisterStatistics.getStatisticsTime().getTime()));
    preparedStatement.setTimestamp(3,new Timestamp(jobRegisterStatistics.getCreationTime().getTime()));
    preparedStatement.execute();
    result=true;
  }
 catch (  final SQLException ex) {
    log.error("Insert jobRegisterStatistics to DB error:",ex);
  }
  return result;
}
