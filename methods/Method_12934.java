/** 
 * Uncapitalize a  {@code String}, changing the first letter to lower case as per  {@link Character#toLowerCase(char)}. No other letters are changed.
 * @param str the {@code String} to uncapitalize
 * @return the uncapitalized {@code String}
 */
public static String uncapitalize(String str){
  return changeFirstCharacterCase(str,false);
}
