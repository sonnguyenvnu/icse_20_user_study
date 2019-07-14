/** 
 * Call to assume that <code>actual</code> satisfies the condition specified by <code>matcher</code>. If not, the test halts and is ignored. Example: <pre>: assumeThat("alwaysPasses", 1, is(1)); // passes foo(); // will execute assumeThat("alwaysFails", 0, is(1)); // assumption failure! test halts int x = 1 / 0; // will never execute </pre>
 * @param < T > the static type accepted by the matcher (this can flag obvious compile-time problems such as {@code assumeThat(1, is("a"))}
 * @param actual the computed value being compared
 * @param matcher an expression, built of {@link Matcher}s, specifying allowed values
 * @see org.hamcrest.CoreMatchers
 * @see org.junit.matchers.JUnitMatchers
 * @deprecated use {@code org.hamcrest.junit.MatcherAssume.assumeThat()}
 */
@Deprecated public static <T>void assumeThat(String message,T actual,Matcher<T> matcher){
  if (!matcher.matches(actual)) {
    throw new AssumptionViolatedException(message,actual,matcher);
  }
}
