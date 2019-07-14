/** 
 * Records an execution and returns true if a retry has been scheduled for the  {@code result} or {@code failure}, else returns false and marks the execution and associated  {@code CompletableFuture} as complete.
 * @throws IllegalStateException if a retry method has already been called or the execution is already complete
 */
public boolean retryFor(Object result,Throwable failure){
  Assert.state(!retryCalled,"Retry has already been called");
  retryCalled=true;
  return !completeOrHandle(result,failure);
}
