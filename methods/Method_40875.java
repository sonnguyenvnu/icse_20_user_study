/** 
 * Records an execution and returns true if a retry can be performed for the  {@code result} or {@code failure}, else returns false and marks the execution as complete.
 * @throws IllegalStateException if the execution is already complete
 */
public boolean canRetryFor(Object result,Throwable failure){
  return !postExecute(new ExecutionResult(result,failure));
}
