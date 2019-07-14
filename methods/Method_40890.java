/** 
 * Calls the asynchronous  {@code supplier} via the configured Scheduler, handling results according to the configuredpolicies. <p> If a configured circuit breaker is open, the resulting future is completed with  {@link CircuitBreakerOpenException}.
 * @param asyncExecution whether this is a detached, async execution that must be manually completed
 * @throws NullPointerException if the {@code supplierFn} is null
 * @throws RejectedExecutionException if the {@code supplierFn} cannot be scheduled for execution
 */
@SuppressWarnings("unchecked") private <T>CompletableFuture<T> callAsync(Function<AsyncExecution,Supplier<CompletableFuture<ExecutionResult>>> supplierFn,boolean asyncExecution){
  FailsafeFuture<T> future=new FailsafeFuture(this);
  AsyncExecution execution=new AsyncExecution(scheduler,future,this);
  future.inject(execution);
  execution.inject(supplierFn.apply(execution),asyncExecution);
  execution.executeAsync(asyncExecution);
  return future;
}
