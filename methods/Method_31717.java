/** 
 * Counts the number of occurrences of this token in this string.
 * @param str   The string to analyse.
 * @param token The token to look for.
 * @return The number of occurrences.
 */
public static int countOccurrencesOf(String str,String token){
  if (str == null || token == null || str.length() == 0 || token.length() == 0) {
    return 0;
  }
  int count=0;
  int pos=0;
  int idx;
  while ((idx=str.indexOf(token,pos)) != -1) {
    ++count;
    pos=idx + token.length();
  }
  return count;
}
