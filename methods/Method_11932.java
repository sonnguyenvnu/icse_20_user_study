/** 
 * Verify that your code throws an exception whose message is matched  by a Hamcrest matcher. <pre> &#064;Test public void throwsExceptionWhoseMessageCompliesWithMatcher() { thrown.expectMessage(startsWith(&quot;What&quot;)); throw new NullPointerException(&quot;What happened?&quot;); }</pre>
 * @deprecated use {@code org.hamcrest.junit.ExpectedException.expectMessage()}
 */
@Deprecated public void expectMessage(Matcher<String> matcher){
  expect(hasMessage(matcher));
}
