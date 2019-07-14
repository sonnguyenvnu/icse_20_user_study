static <T>Supplier<ExecutionResult> resultSupplierOf(CheckedSupplier<T> supplier,AbstractExecution execution){
  return () -> {
    ExecutionResult result=null;
    try {
      result=ExecutionResult.success(supplier.get());
    }
 catch (    Throwable t) {
      result=ExecutionResult.failure(t);
    }
 finally {
      execution.record(result);
    }
    return result;
  }
;
}
