/** 
 * Remove a token from a string.
 * @param inputFragmentStr the input fragment
 * @param formatFragment the format fragment
 */
void remove(String inputFragmentStr,String formatFragment){
  if (inputFragmentStr != null && inputStr.length() >= inputFragmentStr.length()) {
    inputStr=inputStr.substring(inputFragmentStr.length());
  }
  if (formatFragment != null && formatStr.length() >= formatFragment.length()) {
    formatStr=formatStr.substring(formatFragment.length());
  }
}
