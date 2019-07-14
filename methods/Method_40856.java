/** 
 * Records an execution attempt.
 * @throws IllegalStateException if the execution is already complete
 */
void record(ExecutionResult result){
  Assert.state(!completed,"Execution has already been completed");
  attempts.incrementAndGet();
  lastResult=result.getResult();
  lastFailure=result.getFailure();
}
