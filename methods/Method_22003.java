/** 
 * ????.
 */
public final void execute(){
  try {
    jobFacade.checkJobExecutionEnvironment();
  }
 catch (  final JobExecutionEnvironmentException cause) {
    jobExceptionHandler.handleException(jobName,cause);
  }
  ShardingContexts shardingContexts=jobFacade.getShardingContexts();
  if (shardingContexts.isAllowSendJobEvent()) {
    jobFacade.postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_STAGING,String.format("Job '%s' execute begin.",jobName));
  }
  if (jobFacade.misfireIfRunning(shardingContexts.getShardingItemParameters().keySet())) {
    if (shardingContexts.isAllowSendJobEvent()) {
      jobFacade.postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_FINISHED,String.format("Previous job '%s' - shardingItems '%s' is still running, misfired job will start after previous job completed.",jobName,shardingContexts.getShardingItemParameters().keySet()));
    }
    return;
  }
  try {
    jobFacade.beforeJobExecuted(shardingContexts);
  }
 catch (  final Throwable cause) {
    jobExceptionHandler.handleException(jobName,cause);
  }
  execute(shardingContexts,JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER);
  while (jobFacade.isExecuteMisfired(shardingContexts.getShardingItemParameters().keySet())) {
    jobFacade.clearMisfire(shardingContexts.getShardingItemParameters().keySet());
    execute(shardingContexts,JobExecutionEvent.ExecutionSource.MISFIRE);
  }
  jobFacade.failoverIfNecessary();
  try {
    jobFacade.afterJobExecuted(shardingContexts);
  }
 catch (  final Throwable cause) {
    jobExceptionHandler.handleException(jobName,cause);
  }
}
