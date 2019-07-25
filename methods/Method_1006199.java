/** 
 * Check for case-insensitive equality between two strings after removing white space at the beginning and end of the first string.
 * @param one The first string - whitespace is trimmed
 * @param two The second string
 * @return true if the strings are deemed equal
 */
private static boolean comp(String one,String two){
  return one.trim().equalsIgnoreCase(two);
}
