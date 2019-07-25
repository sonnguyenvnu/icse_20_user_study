/** 
 * Returns a  {@link Backoff} that waits an exponentially-increasing amount of time between attempts.
 */
static Backoff exponential(long initialDelayMillis,long maxDelayMillis,double multiplier){
  return new ExponentialBackoff(initialDelayMillis,maxDelayMillis,multiplier);
}
