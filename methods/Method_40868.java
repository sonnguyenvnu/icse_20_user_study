/** 
 * Sets the ratio of successive successful executions that must occur when in a half-open state in order to close the circuit. For example: 5, 10 would close the circuit if 5 out of the last 10 executions were successful. The circuit will not be closed until at least the given number of  {@code executions} have taken place.
 * @param successes The number of successful executions that must occur in order to open the circuit
 * @param executions The number of executions to measure the {@code successes} against
 * @throws IllegalArgumentException if {@code successes} < 1, {@code executions} < 1, or {@code successes} is >{@code executions}
 */
public synchronized CircuitBreaker<R> withSuccessThreshold(int successes,int executions){
  Assert.isTrue(successes >= 1,"successes must be greater than or equal to 1");
  Assert.isTrue(executions >= 1,"executions must be greater than or equal to 1");
  Assert.isTrue(executions >= successes,"executions must be greater than or equal to successes");
  this.successThreshold=new Ratio(successes,executions);
  state.get().setSuccessThreshold(successThreshold);
  return this;
}
