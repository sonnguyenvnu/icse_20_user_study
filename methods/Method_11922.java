/** 
 * @return A matcher that delegates to exceptionMatcher and in additionappends the stacktrace of the actual Exception in case of a mismatch.
 */
public static <T extends Exception>Matcher<T> isException(Matcher<T> exceptionMatcher){
  return StacktracePrintingMatcher.isException(exceptionMatcher);
}
