/** 
 * <p>Uncapitalizes a String changing the first letter to title case as per  {@link Character#toLowerCase(char)}. No other letters are changed.</p> <p>For a word based algorithm, see  {@link external.org.apache.commons.lang3.text.WordUtils#uncapitalize(String)}. A  {@code null} input String returns {@code null}.</p> <pre> StringUtils.uncapitalize(null)  = null StringUtils.uncapitalize("")    = "" StringUtils.uncapitalize("Cat") = "cat" StringUtils.uncapitalize("CAT") = "cAT" </pre>
 * @param str the String to uncapitalize, may be null
 * @return the uncapitalized String, {@code null} if null String input
 * @see external.org.apache.commons.lang3.text.WordUtils#uncapitalize(String)
 * @see #capitalize(String)
 * @since 2.0
 */
public static String uncapitalize(String str){
  int strLen;
  if (str == null || (strLen=str.length()) == 0) {
    return str;
  }
  return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
}
