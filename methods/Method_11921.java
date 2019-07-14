/** 
 * This is useful for fluently combining matchers that must both pass.  For example: <pre> assertThat(string, both(containsString("a")).and(containsString("b"))); </pre>
 * @deprecated Please use {@link CoreMatchers#both(Matcher)} instead.
 */
@Deprecated public static <T>CombinableBothMatcher<T> both(Matcher<? super T> matcher){
  return CoreMatchers.both(matcher);
}
