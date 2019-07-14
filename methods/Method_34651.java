public static ExecutionResult from(HystrixEventType... eventTypes){
  boolean didExecutionOccur=false;
  for (  HystrixEventType eventType : eventTypes) {
    if (didExecutionOccur(eventType)) {
      didExecutionOccur=true;
    }
  }
  return new ExecutionResult(new EventCounts(eventTypes),-1L,-1,-1,null,null,didExecutionOccur,false,null);
}
