/** 
 * Executes the  {@code supplier} asynchronously until the resulting future is successfully completed or the configuredpolicies are exceeded. <p> If a configured circuit breaker is open, the resulting future is completed exceptionally with  {@link CircuitBreakerOpenException}.
 * @throws NullPointerException if the {@code supplier} is null
 * @throws RejectedExecutionException if the {@code supplier} cannot be scheduled for execution
 */
public <T extends R>CompletableFuture<T> getStageAsync(CheckedSupplier<? extends CompletionStage<T>> supplier){
  return callAsync(execution -> Functions.promiseOfStage(supplier,execution),false);
}
