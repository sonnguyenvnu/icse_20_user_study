/** 
 * Sets the ratio of successive failures that must occur when in a closed state in order to open the circuit. For example: 5, 10 would open the circuit if 5 out of the last 10 executions result in a failure. The circuit will not be opened until at least the given number of  {@code executions} have taken place.
 * @param failures The number of failures that must occur in order to open the circuit
 * @param executions The number of executions to measure the {@code failures} against
 * @throws IllegalArgumentException if {@code failures} < 1, {@code executions} < 1, or {@code failures} is >{@code executions}
 */
public synchronized CircuitBreaker<R> withFailureThreshold(int failures,int executions){
  Assert.isTrue(failures >= 1,"failures must be greater than or equal to 1");
  Assert.isTrue(executions >= 1,"executions must be greater than or equal to 1");
  Assert.isTrue(executions >= failures,"executions must be greater than or equal to failures");
  this.failureThreshold=new Ratio(failures,executions);
  state.get().setFailureThreshold(failureThreshold);
  return this;
}
