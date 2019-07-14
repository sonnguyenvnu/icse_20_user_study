/** 
 * Returns <code>true</code> if character is a white space ( {@code <= ' '}). White space definition is taken from String class (see: <code>trim()</code>). This method has different results then <code>Character#isWhitespace</code>."
 */
public static boolean isWhitespace(final char c){
  return c <= ' ';
}
