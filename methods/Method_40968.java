/** 
 * Sets the max number of execution attempts to perform.  {@code -1} indicates no limit. This method has the sameeffect as setting 1 more than  {@link #withMaxRetries(int)}. For example, 2 retries equal 3 attempts.
 * @throws IllegalArgumentException if {@code maxAttempts} is 0 or less than -1
 * @see #withMaxRetries(int)
 */
public RetryPolicy<R> withMaxAttempts(int maxAttempts){
  Assert.isTrue(maxAttempts != 0,"maxAttempts cannot be 0");
  Assert.isTrue(maxAttempts >= -1,"maxAttempts cannot be less than -1");
  this.maxRetries=maxAttempts == -1 ? -1 : maxAttempts - 1;
  return this;
}
