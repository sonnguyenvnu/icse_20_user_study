static Supplier<CompletableFuture<ExecutionResult>> promiseOf(CheckedRunnable runnable,AbstractExecution execution){
  Assert.notNull(runnable,"runnable");
  return () -> {
    ExecutionResult result;
    try {
      execution.preExecute();
      runnable.run();
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
