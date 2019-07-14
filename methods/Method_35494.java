/** 
 * Tests if the value should be tried as a decimal. It makes no test if there are actual digits.
 * @param val value to test
 * @return true if the string is "-0" or if it contains '.', 'e', or 'E', false otherwise.
 */
protected static boolean isDecimalNotation(final String val){
  return val.indexOf('.') > -1 || val.indexOf('e') > -1 || val.indexOf('E') > -1 || "-0".equals(val);
}
