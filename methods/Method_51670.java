/** 
 * Returns whether the non-null text arg matches any of the test values.
 * @param text
 * @param tests
 * @return boolean
 */
public static boolean isAnyOf(String text,String... tests){
  for (  String test : tests) {
    if (text.equals(test)) {
      return true;
    }
  }
  return false;
}
