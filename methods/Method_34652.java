public ExecutionResult setException(Exception e){
  return new ExecutionResult(eventCounts,startTimestamp,executionLatency,userThreadLatency,e,executionException,executionOccurred,isExecutedInThread,collapserKey);
}
