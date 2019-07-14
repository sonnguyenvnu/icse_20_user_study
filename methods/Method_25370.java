/** 
 * Compose several matchers together, such that the composite matches an AST node iff all the given matchers do.
 */
@SafeVarargs public static <T extends Tree>Matcher<T> allOf(final Matcher<? super T>... matchers){
  return (t,state) -> {
    for (    Matcher<? super T> matcher : matchers) {
      if (!matcher.matches(t,state)) {
        return false;
      }
    }
    return true;
  }
;
}
