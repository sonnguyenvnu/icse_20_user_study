/** 
 * Returns <code>true</code> if specified character is lowercase ASCII. If user uses only ASCIIs, it is much much faster.
 */
public static boolean isLowercaseAlpha(final char c){
  return (c >= 'a') && (c <= 'z');
}
