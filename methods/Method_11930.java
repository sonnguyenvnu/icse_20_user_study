/** 
 * Verify that your code throws an exception that is matched by a Hamcrest matcher. <pre> &#064;Test public void throwsExceptionThatCompliesWithMatcher() { NullPointerException e = new NullPointerException(); thrown.expect(is(e)); throw e; }</pre>
 * @deprecated use {@code org.hamcrest.junit.ExpectedException.expect()}
 */
@Deprecated public void expect(Matcher<?> matcher){
  matcherBuilder.add(matcher);
}
