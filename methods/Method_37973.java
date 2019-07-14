/** 
 * Determines if string is not blank.
 */
public static boolean isNotBlank(final CharSequence string){
  return ((string != null) && !containsOnlyWhitespaces(string));
}
