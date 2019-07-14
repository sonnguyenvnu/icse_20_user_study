/** 
 * ??????????.
 * @param condition ????
 * @return ??????????
 */
public Result<JobStatusTraceEvent> findJobStatusTraceEvents(final Condition condition){
  return new Result<>(getEventCount(TABLE_JOB_STATUS_TRACE_LOG,FIELDS_JOB_STATUS_TRACE_LOG,condition),getJobStatusTraceEvents(condition));
}
