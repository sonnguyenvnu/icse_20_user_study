/** 
 * Matches calls to the method  {@code org.junit.Assert#assertEquals} and corresponding methods inJUnit 3.x.
 */
public static Matcher<ExpressionTree> assertEqualsInvocation(){
  return ASSERT_EQUALS;
}
