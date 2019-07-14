static <T>Supplier<CompletableFuture<ExecutionResult>> asyncOfFutureExecution(AsyncSupplier<? extends CompletionStage<? extends T>> supplier,AsyncExecution execution){
  Assert.notNull(supplier,"supplier");
  return new Supplier<CompletableFuture<ExecutionResult>>(){
    @Override public CompletableFuture<ExecutionResult> get(){
      try {
        execution.preExecute();
        asyncFutureLock.acquire();
        supplier.get(execution).whenComplete((innerResult,failure) -> {
          try {
            if (failure != null)             execution.completeOrHandle(innerResult,failure instanceof CompletionException ? failure.getCause() : failure);
          }
  finally {
            asyncFutureLock.release();
          }
        }
);
      }
 catch (      Throwable e) {
        try {
          execution.completeOrHandle(null,e);
        }
  finally {
          asyncFutureLock.release();
        }
      }
      return NULL_FUTURE;
    }
  }
;
}
