/** 
 * Matches if there are  {@code count} failures
 */
public static Matcher<PrintableResult> failureCountIs(final int count){
  return new TypeSafeMatcher<PrintableResult>(){
    public void describeTo(    Description description){
      description.appendText("has " + count + " failures");
    }
    @Override public boolean matchesSafely(    PrintableResult item){
      return item.failureCount() == count;
    }
  }
;
}
