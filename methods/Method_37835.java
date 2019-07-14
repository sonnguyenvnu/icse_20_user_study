/** 
 * Returns <code>true</code> if specified character is uppercase ASCII. If user uses only ASCIIs, it is much much faster.
 */
public static boolean isUppercaseAlpha(final char c){
  return (c >= 'A') && (c <= 'Z');
}
