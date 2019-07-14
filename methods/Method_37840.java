/** 
 * Indicates whether the given character is the hexadecimal digit.
 */
public static boolean isHexDigit(final char c){
  return (c >= '0' && c <= '9') || ((c >= 'a') && (c <= 'f')) || ((c >= 'A') && (c <= 'F'));
}
