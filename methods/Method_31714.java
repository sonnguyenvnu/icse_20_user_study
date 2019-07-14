/** 
 * Checks whether this string is not  {@code null} and not <i>empty</i>.
 * @param str The string to check.
 * @return {@code true} if it has content, {@code false} if it is {@code null} or blank.
 */
public static boolean hasLength(String str){
  return str != null && str.length() > 0;
}
