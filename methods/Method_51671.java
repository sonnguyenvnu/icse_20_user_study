/** 
 * Checks for the existence of any of the listed prefixes on the non-null text and removes them.
 * @param text
 * @param prefixes
 * @return String
 */
public static String withoutPrefixes(String text,String... prefixes){
  for (  String prefix : prefixes) {
    if (text.startsWith(prefix)) {
      return text.substring(prefix.length());
    }
  }
  return text;
}
