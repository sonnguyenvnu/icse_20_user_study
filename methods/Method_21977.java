/** 
 * ??????????.
 * @param condition ????
 * @return ??????????
 */
public Result<JobExecutionEvent> findJobExecutionEvents(final Condition condition){
  return new Result<>(getEventCount(TABLE_JOB_EXECUTION_LOG,FIELDS_JOB_EXECUTION_LOG,condition),getJobExecutionEvents(condition));
}
