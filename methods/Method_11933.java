/** 
 * Check if any Exception is expected.
 * @since 4.13
 */
public final boolean isAnyExceptionExpected(){
  return matcherBuilder.expectsThrowable();
}
