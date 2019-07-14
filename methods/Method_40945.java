/** 
 * Performs a synchronous execution by first doing a pre-execute, calling the next executor, else calling the executor's supplier, then finally doing a post-execute.
 */
protected Supplier<ExecutionResult> supply(Supplier<ExecutionResult> supplier){
  return () -> {
    ExecutionResult result=preExecute();
    if (result != null)     return result;
    return postExecute(supplier.get());
  }
;
}
