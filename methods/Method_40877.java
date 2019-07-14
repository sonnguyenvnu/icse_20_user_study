/** 
 * Performs a synchronous execution.
 */
ExecutionResult executeSync(Supplier<ExecutionResult> supplier){
  for (  PolicyExecutor<Policy<Object>> policyExecutor : policyExecutors)   supplier=policyExecutor.supply(supplier);
  ExecutionResult result=supplier.get();
  completed=result.isComplete();
  executor.handleComplete(result,this);
  return result;
}
