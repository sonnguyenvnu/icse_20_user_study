@SuppressWarnings("unchecked") protected CompletableFuture<ExecutionResult> onFailureAsync(ExecutionResult result,Scheduler scheduler,FailsafeFuture<Object> future){
  if (!policy.isAsync())   return CompletableFuture.completedFuture(onFailure(result));
  CompletableFuture<ExecutionResult> promise=new CompletableFuture<>();
  Callable<Object> callable=() -> promise.complete(onFailure(result));
  try {
    future.inject((Future)scheduler.schedule(callable,result.getWaitNanos(),TimeUnit.NANOSECONDS));
  }
 catch (  Exception e) {
    promise.completeExceptionally(e);
  }
  return promise;
}
