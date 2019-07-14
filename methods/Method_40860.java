/** 
 * Attempts to complete the execution and the associated  {@code CompletableFuture} with the {@code result} and {@code failure}. Returns true on success, else false if completion failed and the execution should be retried via  {@link #retry()}. <p> Note: the execution may be completed even when the  {@code failure} is not {@code null}, such as when the RetryPolicy does not allow retries for the  {@code failure}.
 * @throws IllegalStateException if the execution is already complete
 */
public boolean complete(Object result,Throwable failure){
  return postExecute(new ExecutionResult(result,failure));
}
