/** 
 * Matches calls to the method  {@code org.junit.Assert#assertNotEquals} and corresponding methodsin JUnit 3.x.
 */
public static Matcher<ExpressionTree> assertNotEqualsInvocation(){
  return ASSERT_NOT_EQUALS;
}
