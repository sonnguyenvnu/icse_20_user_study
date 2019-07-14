static <T>Supplier<CompletableFuture<ExecutionResult>> promiseOf(CheckedSupplier<T> supplier,AbstractExecution execution){
  Assert.notNull(supplier,"supplier");
  return () -> {
    ExecutionResult result;
    try {
      execution.preExecute();
      result=ExecutionResult.success(supplier.get());
    }
 catch (    Throwable e) {
      result=ExecutionResult.failure(e);
    }
    execution.record(result);
    return CompletableFuture.completedFuture(result);
  }
;
}
