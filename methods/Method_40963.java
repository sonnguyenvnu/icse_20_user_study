/** 
 * Returns the max number of execution attempts to perform. A value of  {@code -1} represents no limit. Defaults to{@code 3}.
 * @see #withMaxAttempts(int)
 * @see #getMaxRetries()
 */
public int getMaxAttempts(){
  return maxRetries == -1 ? -1 : maxRetries + 1;
}
