/** 
 * Matches if the result has exactly one failure, and it contains  {@code string}
 */
public static Matcher<Object> hasSingleFailureContaining(final String string){
  return new BaseMatcher<Object>(){
    public boolean matches(    Object item){
      return item.toString().contains(string) && failureCountIs(1).matches(item);
    }
    public void describeTo(    Description description){
      description.appendText("has single failure containing " + string);
    }
  }
;
}
