/** 
 * Capitalize a  {@code String}, changing the first letter to upper case as per  {@link Character#toUpperCase(char)}. No other letters are changed.
 * @param str the {@code String} to capitalize
 * @return the capitalized {@code String}
 */
public static String capitalize(String str){
  return changeFirstCharacterCase(str,true);
}
