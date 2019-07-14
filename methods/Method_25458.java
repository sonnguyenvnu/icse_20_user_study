/** 
 * Determines if a string is empty or consists only of whitespace
 * @param source The string to check
 * @return True if the string is empty or contains only whitespace, false otherwise
 */
private static boolean isEmptyOrWhitespace(String source){
  return source == null || source.matches("\\s*");
}
