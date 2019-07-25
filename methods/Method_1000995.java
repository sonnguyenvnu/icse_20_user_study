/** 
 * Returns either `text` or [null].
 */
public static String backticked(String text){
  if (text == null) {
    return "[null]";
  }
  return new StringBuilder(text.length() + 2).append('`').append(text).append('`').toString();
}
