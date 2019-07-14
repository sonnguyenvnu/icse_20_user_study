/** 
 * Sets the max number of retries to perform when an execution attempt fails.  {@code -1} indicates no limit. Thismethod has the same effect as setting 1 less than  {@link #withMaxAttempts(int)}. For example, 2 retries equal 3 attempts.
 * @throws IllegalArgumentException if {@code maxRetries} &lt -1
 * @see #withMaxAttempts(int)
 */
public RetryPolicy<R> withMaxRetries(int maxRetries){
  Assert.isTrue(maxRetries >= -1,"maxRetries must be greater than or equal to -1");
  this.maxRetries=maxRetries;
  return this;
}
