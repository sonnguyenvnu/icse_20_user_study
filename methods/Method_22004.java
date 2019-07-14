private void execute(final ShardingContexts shardingContexts,final JobExecutionEvent.ExecutionSource executionSource){
  if (shardingContexts.getShardingItemParameters().isEmpty()) {
    if (shardingContexts.isAllowSendJobEvent()) {
      jobFacade.postJobStatusTraceEvent(shardingContexts.getTaskId(),State.TASK_FINISHED,String.format("Sharding item for job '%s' is empty.",jobName));
    }
    return;
  }
  jobFacade.registerJobBegin(shardingContexts);
  String taskId=shardingContexts.getTaskId();
  if (shardingContexts.isAllowSendJobEvent()) {
    jobFacade.postJobStatusTraceEvent(taskId,State.TASK_RUNNING,"");
  }
  try {
    process(shardingContexts,executionSource);
  }
  finally {
    jobFacade.registerJobCompleted(shardingContexts);
    if (itemErrorMessages.isEmpty()) {
      if (shardingContexts.isAllowSendJobEvent()) {
        jobFacade.postJobStatusTraceEvent(taskId,State.TASK_FINISHED,"");
      }
    }
 else {
      if (shardingContexts.isAllowSendJobEvent()) {
        jobFacade.postJobStatusTraceEvent(taskId,State.TASK_ERROR,itemErrorMessages.toString());
      }
    }
  }
}
