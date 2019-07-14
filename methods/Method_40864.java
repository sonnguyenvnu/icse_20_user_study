/** 
 * Records an execution that is about to take place by incrementing the internal executions count. Required for standalone CircuitBreaker usage.
 */
public void preExecute(){
  currentExecutions.incrementAndGet();
}
