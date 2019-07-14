boolean addJobStatusTraceEvent(final JobStatusTraceEvent jobStatusTraceEvent){
  String originalTaskId=jobStatusTraceEvent.getOriginalTaskId();
  if (State.TASK_STAGING != jobStatusTraceEvent.getState()) {
    originalTaskId=getOriginalTaskId(jobStatusTraceEvent.getTaskId());
  }
  boolean result=false;
  String sql="INSERT INTO `" + TABLE_JOB_STATUS_TRACE_LOG + "` (`id`, `job_name`, `original_task_id`, `task_id`, `slave_id`, `source`, `execution_type`, `sharding_item`,  " + "`state`, `message`, `creation_time`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
  try (Connection conn=dataSource.getConnection();PreparedStatement preparedStatement=conn.prepareStatement(sql)){
    preparedStatement.setString(1,UUID.randomUUID().toString());
    preparedStatement.setString(2,jobStatusTraceEvent.getJobName());
    preparedStatement.setString(3,originalTaskId);
    preparedStatement.setString(4,jobStatusTraceEvent.getTaskId());
    preparedStatement.setString(5,jobStatusTraceEvent.getSlaveId());
    preparedStatement.setString(6,jobStatusTraceEvent.getSource().toString());
    preparedStatement.setString(7,jobStatusTraceEvent.getExecutionType().name());
    preparedStatement.setString(8,jobStatusTraceEvent.getShardingItems());
    preparedStatement.setString(9,jobStatusTraceEvent.getState().toString());
    preparedStatement.setString(10,truncateString(jobStatusTraceEvent.getMessage()));
    preparedStatement.setTimestamp(11,new Timestamp(jobStatusTraceEvent.getCreationTime().getTime()));
    preparedStatement.execute();
    result=true;
  }
 catch (  final SQLException ex) {
    log.error(ex.getMessage());
  }
  return result;
}
