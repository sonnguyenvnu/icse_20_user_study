/** 
 * Sets the  {@code jitterFactor} to randomly vary retry delays by. For each retry delay, a random portion of the delaymultiplied by the  {@code jitterFactor} will be added or subtracted to the delay. For example: a retry delay of{@code 100} milliseconds and a {@code jitterFactor} of {@code .25} will result in a random retry delay between{@code 75} and {@code 125} milliseconds.<p> Jitter should be combined with  {@link #withDelay(Duration) fixed},  {@link #withDelay(long,long,ChronoUnit) random} or {@link #withBackoff(long,long,ChronoUnit) exponential backoff} delays.
 * @throws IllegalArgumentException if {@code jitterFactor} is < 0 or > 1
 * @throws IllegalStateException if no delay has been configured or {@link #withJitter(Duration)} has already beencalled
 */
public RetryPolicy<R> withJitter(double jitterFactor){
  Assert.isTrue(jitterFactor >= 0.0 && jitterFactor <= 1.0,"jitterFactor must be >= 0 and <= 1");
  Assert.state(delay != null || delayMin != null,"A delay must be configured");
  Assert.state(jitter == null,"withJitter(Duration) has already been called");
  this.jitterFactor=jitterFactor;
  return this;
}
