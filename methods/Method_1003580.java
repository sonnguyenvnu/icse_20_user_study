/** 
 * Quote the given  {@code String} with single quotes.
 * @param str the input {@code String} (e.g. "myString").
 * @return the quoted {@code String} (e.g. "'myString'"), or null if the input was null.
 */
public static String quote(String str){
  return (str != null ? "'" + str + "'" : null);
}
