/** 
 * Returns whether the policy allows retries according to the configured  {@link #withMaxRetries(int) maxRetries} and{@link #withMaxDuration(Duration) maxDuration}.
 * @see #withMaxRetries(int)
 * @see #withMaxDuration(Duration)
 */
public boolean allowsRetries(){
  return (maxRetries == -1 || maxRetries > 0) && (maxDuration == null || maxDuration.toNanos() > 0);
}
