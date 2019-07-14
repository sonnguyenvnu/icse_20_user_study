/** 
 * Sets the  {@code delay} between retries, exponentially backing off to the {@code maxDelay} and multiplyingsuccessive delays by a factor of 2.
 * @throws NullPointerException if {@code chronoUnit} is null
 * @throws IllegalArgumentException if {@code delay} is <= 0 or {@code delay} is >= {@code maxDelay}
 * @throws IllegalStateException if {@code delay} is >= the {@link RetryPolicy#withMaxDuration(Duration) maxDuration}, if delays have already been set, or if random delays have already been set
 */
public RetryPolicy<R> withBackoff(long delay,long maxDelay,ChronoUnit chronoUnit){
  return withBackoff(delay,maxDelay,chronoUnit,2);
}
