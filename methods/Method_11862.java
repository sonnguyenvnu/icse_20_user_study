/** 
 * Returns a matcher that verifies that the outer exception has a cause for which the supplied matcher evaluates to true.
 * @param matcher to apply to the cause of the outer exception
 * @param < T > type of the outer exception
 */
@Factory public static <T extends Throwable>Matcher<T> hasCause(final Matcher<?> matcher){
  return new ThrowableCauseMatcher<T>(matcher);
}
