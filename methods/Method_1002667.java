/** 
 * Returns a  {@link Backoff} that waits a fixed delay between attempts.
 */
static Backoff fixed(long delayMillis){
  return new FixedBackoff(delayMillis);
}
