public ExecutionResult markUserThreadCompletion(long userThreadLatency){
  if (startTimestamp > 0 && !isResponseRejected()) {
    return new ExecutionResult(eventCounts,startTimestamp,executionLatency,(int)userThreadLatency,failedExecutionException,executionException,executionOccurred,isExecutedInThread,collapserKey);
  }
 else {
    return this;
  }
}
