/** 
 * Quote the given String with single quotes.
 * @param str the input String (e.g. "myString")
 * @return the quoted String (e.g. "'myString'"), or <code>null<code> if the input was <code>null</code>
 */
public static String quote(String str){
  return (str != null ? "'" + str + "'" : null);
}
