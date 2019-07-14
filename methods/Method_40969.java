/** 
 * Sets the max duration to perform retries for, else the execution will be failed.
 * @throws NullPointerException if {@code maxDuration} is null
 * @throws IllegalStateException if {@code maxDuration} is <= the {@link RetryPolicy#withDelay(Duration) delay}
 */
public RetryPolicy<R> withMaxDuration(Duration maxDuration){
  Assert.notNull(maxDuration,"maxDuration");
  Assert.state(maxDuration.toNanos() > delay.toNanos(),"maxDuration must be greater than the delay");
  this.maxDuration=maxDuration;
  return this;
}
