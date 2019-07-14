/** 
 * Checks whether this string isn't  {@code null} and contains at least one non-blank character.
 * @param s The string to check.
 * @return {@code true} if it has text, {@code false} if not.
 */
public static boolean hasText(String s){
  return (s != null) && (s.trim().length() > 0);
}
