static Supplier<CompletableFuture<ExecutionResult>> asyncOfExecution(AsyncRunnable runnable,AsyncExecution execution){
  Assert.notNull(runnable,"runnable");
  return new Supplier<CompletableFuture<ExecutionResult>>(){
    @Override public synchronized CompletableFuture<ExecutionResult> get(){
      try {
        execution.preExecute();
        runnable.run(execution);
      }
 catch (      Throwable e) {
        execution.completeOrHandle(null,e);
      }
      return NULL_FUTURE;
    }
  }
;
}
