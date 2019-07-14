static Supplier<CompletableFuture<ExecutionResult>> promiseOf(ContextualRunnable runnable,AbstractExecution execution){
  Assert.notNull(runnable,"runnable");
  return () -> {
    ExecutionResult result;
    try {
      execution.preExecute();
      runnable.run(execution);
      result=ExecutionResult.NONE;
    }
 catch (    Throwable e) {
      result=ExecutionResult.failure(e);
    }
    execution.record(result);
    return CompletableFuture.completedFuture(result);
  }
;
}
