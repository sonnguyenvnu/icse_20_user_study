/** 
 * Returns true for strings that contain format specifiers. 
 */
private static boolean missingFormatArgs(String value){
  try {
    Formatter.check(value);
  }
 catch (  MissingFormatArgumentException e) {
    return true;
  }
catch (  Exception ignored) {
  }
  return false;
}
