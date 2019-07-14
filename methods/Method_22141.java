/** 
 * ????????????.
 * @param from ??????
 * @return ??????????
 */
public List<JobRegisterStatistics> findJobRegisterStatistics(final Date from){
  List<JobRegisterStatistics> result=new LinkedList<>();
  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String sql=String.format("SELECT id, registered_count, statistics_time, creation_time FROM %s WHERE statistics_time >= '%s' order by id ASC",TABLE_JOB_REGISTER_STATISTICS,formatter.format(from));
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql);ResultSet resultSet=preparedStatement.executeQuery()){
    while (resultSet.next()) {
      JobRegisterStatistics jobRegisterStatistics=new JobRegisterStatistics(resultSet.getLong(1),resultSet.getInt(2),new Date(resultSet.getTimestamp(3).getTime()),new Date(resultSet.getTimestamp(4).getTime()));
      result.add(jobRegisterStatistics);
    }
  }
 catch (  final SQLException ex) {
    log.error("Fetch jobRegisterStatistics from DB error:",ex);
  }
  return result;
}
