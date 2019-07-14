/** 
 * Performs potentially asynchronous post-execution handling for a  {@code result}.
 */
protected CompletableFuture<ExecutionResult> postExecuteAsync(ExecutionResult result,Scheduler scheduler,FailsafeFuture<Object> future){
  if (isFailure(result)) {
    result=result.with(false,false);
    return onFailureAsync(result,scheduler,future).whenComplete((postResult,error) -> {
      callFailureListener(postResult);
    }
);
  }
 else {
    result=result.with(true,true);
    onSuccess(result);
    callSuccessListener(result);
    return CompletableFuture.completedFuture(result);
  }
}
