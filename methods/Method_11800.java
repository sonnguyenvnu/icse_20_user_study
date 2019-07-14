/** 
 * Matches if the result has exactly one failure matching the given matcher.
 * @since 4.13
 */
public static Matcher<PrintableResult> hasSingleFailureMatching(final Matcher<Throwable> matcher){
  return new TypeSafeMatcher<PrintableResult>(){
    @Override public boolean matchesSafely(    PrintableResult item){
      return item.failureCount() == 1 && matcher.matches(item.failures().get(0).getException());
    }
    public void describeTo(    Description description){
      description.appendText("has failure with exception matching ");
      matcher.describeTo(description);
    }
  }
;
}
