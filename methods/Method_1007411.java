/** 
 * Uncapitalize a  {@code String}, changing the first letter to lower case as per  {@link Character#toLowerCase(char)}. No other letters are changed.
 * @param str the  {@code String} to uncapitalize, may be {@code null}
 * @return the uncapitalized {@code String}, or  {@code null} if the suppliedstring is  {@code null}
 */
public static String uncapitalize(String str){
  return changeFirstCharacterCase(str,false);
}
