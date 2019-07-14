/** 
 * Executes the  {@code runnable} asynchronously until successful or until the configured policies are exceeded. Thismethod is intended for integration with asynchronous code. Retries must be manually scheduled via one of the  {@code AsyncExecution.retry} methods.<p> If a configured circuit breaker is open, the resulting future is completed with  {@link CircuitBreakerOpenException}.
 * @throws NullPointerException if the {@code runnable} is null
 * @throws RejectedExecutionException if the {@code runnable} cannot be scheduled for execution
 */
public CompletableFuture<Void> runAsyncExecution(AsyncRunnable runnable){
  return callAsync(execution -> Functions.asyncOfExecution(runnable,execution),true);
}
