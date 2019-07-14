/** 
 * Sets the  {@code delay} between retries, exponentially backing off to the {@code maxDelay} and multiplyingsuccessive delays by the  {@code delayFactor}.
 * @throws NullPointerException if {@code chronoUnit} is null
 * @throws IllegalArgumentException if {@code delay} <= 0, {@code delay} is >= {@code maxDelay}, or the  {@code delayFactor} is <= 1
 * @throws IllegalStateException if {@code delay} is >= the {@link RetryPolicy#withMaxDuration(Duration) maxDuration}, if delays have already been set, or if random delays have already been set
 */
public RetryPolicy<R> withBackoff(long delay,long maxDelay,ChronoUnit chronoUnit,double delayFactor){
  Assert.notNull(chronoUnit,"chronoUnit");
  Assert.isTrue(delay > 0,"The delay must be greater than 0");
  Duration delayDuration=Duration.of(delay,chronoUnit);
  Duration maxDelayDuration=Duration.of(maxDelay,chronoUnit);
  Assert.state(maxDuration == null || delayDuration.toNanos() < maxDuration.toNanos(),"delay must be less than the maxDuration");
  Assert.isTrue(delayDuration.toNanos() < maxDelayDuration.toNanos(),"delay must be less than the maxDelay");
  Assert.isTrue(delayFactor > 1,"delayFactor must be greater than 1");
  Assert.state(this.delay == null || this.delay.equals(Duration.ZERO),"Delays have already been set");
  Assert.state(delayMin == null,"Random delays have already been set");
  this.delay=delayDuration;
  this.maxDelay=maxDelayDuration;
  this.delayFactor=delayFactor;
  return this;
}
