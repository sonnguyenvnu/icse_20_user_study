/** 
 * Matches if the tests are all successful
 */
public static Matcher<PrintableResult> isSuccessful(){
  return failureCountIs(0);
}
