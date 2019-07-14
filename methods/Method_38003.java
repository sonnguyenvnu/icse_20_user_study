/** 
 * Strips leading and trailing char from given string.
 */
public static String stripChar(final String string,final char c){
  if (string.length() == 0) {
    return string;
  }
  if (string.length() == 1) {
    if (string.charAt(0) == c) {
      return StringPool.EMPTY;
    }
    return string;
  }
  int left=0;
  int right=string.length();
  if (string.charAt(left) == c) {
    left++;
  }
  if (string.charAt(right - 1) == c) {
    right--;
  }
  return string.substring(left,right);
}
