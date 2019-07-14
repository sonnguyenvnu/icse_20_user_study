static <T>Supplier<CompletableFuture<ExecutionResult>> asyncOfExecution(AsyncSupplier<T> supplier,AsyncExecution execution){
  Assert.notNull(supplier,"supplier");
  return new Supplier<CompletableFuture<ExecutionResult>>(){
    @Override public synchronized CompletableFuture<ExecutionResult> get(){
      try {
        execution.preExecute();
        supplier.get(execution);
      }
 catch (      Throwable e) {
        execution.completeOrHandle(null,e);
      }
      return NULL_FUTURE;
    }
  }
;
}
