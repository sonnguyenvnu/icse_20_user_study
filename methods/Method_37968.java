/** 
 * Replaces the very first occurrence of a substring with supplied string.
 * @param s      source string
 * @param sub    substring to replace
 * @param with   substring to replace with
 */
public static String replaceFirst(final String s,final String sub,final String with){
  int i=s.indexOf(sub);
  if (i == -1) {
    return s;
  }
  return s.substring(0,i) + with + s.substring(i + sub.length());
}
