/** 
 * Capitalize a  {@code String}, changing the first letter to upper case as per  {@link Character#toUpperCase(char)}. No other letters are changed.
 * @param str the  {@code String} to capitalize, may be {@code null}
 * @return the capitalized {@code String}, or  {@code null} if the suppliedstring is  {@code null}
 */
public static String capitalize(String str){
  return changeFirstCharacterCase(str,true);
}
