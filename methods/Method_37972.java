/** 
 * Determines if a string is blank (<code>null</code> or  {@link #containsOnlyWhitespaces(CharSequence)}).
 */
public static boolean isBlank(final CharSequence string){
  return ((string == null) || containsOnlyWhitespaces(string));
}
