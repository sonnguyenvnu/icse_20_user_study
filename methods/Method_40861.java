/** 
 * Records an execution and returns true if a retry has been scheduled for else returns returns false and completes the execution and associated  {@code CompletableFuture}.
 * @throws IllegalStateException if a retry method has already been called or the execution is already complete
 */
public boolean retry(){
  return retryFor(lastResult,lastFailure);
}
