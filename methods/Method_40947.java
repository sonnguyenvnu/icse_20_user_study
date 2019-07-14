/** 
 * Performs an async execution by first doing an optional pre-execute, calling the next executor, else scheduling the executor's supplier, then finally doing an async post-execute.
 */
protected Supplier<CompletableFuture<ExecutionResult>> supplyAsync(Supplier<CompletableFuture<ExecutionResult>> supplier,Scheduler scheduler,FailsafeFuture<Object> future){
  return () -> {
    ExecutionResult result=preExecute();
    if (result != null)     return CompletableFuture.completedFuture(result);
    return supplier.get().thenCompose(s -> postExecuteAsync(s,scheduler,future));
  }
;
}
