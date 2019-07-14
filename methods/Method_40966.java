/** 
 * Sets the  {@code delay} to occur between retries.
 * @throws NullPointerException if {@code chronoUnit} is null
 * @throws IllegalArgumentException if {@code delay} <= 0
 * @throws IllegalStateException if {@code delay} is >= the {@link RetryPolicy#withMaxDuration(Duration) maxDuration}, if random delays have already been set, or if backoff delays have already been set
 */
public RetryPolicy<R> withDelay(Duration delay){
  Assert.notNull(delay,"delay");
  Assert.isTrue(delay.toNanos() > 0,"delay must be greater than 0");
  Assert.state(maxDuration == null || delay.toNanos() < maxDuration.toNanos(),"delay must be less than the maxDuration");
  Assert.state(delayMin == null,"Random delays have already been set");
  Assert.state(maxDelay == null,"Backoff delays have already been set");
  this.delay=delay;
  return this;
}
