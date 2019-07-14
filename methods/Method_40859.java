void inject(Supplier<CompletableFuture<ExecutionResult>> supplier,boolean asyncExecution){
  if (!asyncExecution) {
    outerExecutionSupplier=supplier;
  }
 else {
    outerExecutionSupplier=innerExecutionSupplier=Functions.settableSupplierOf(supplier);
  }
  for (  PolicyExecutor<Policy<Object>> policyExecutor : policyExecutors)   outerExecutionSupplier=policyExecutor.supplyAsync(outerExecutionSupplier,scheduler,this.future);
}
