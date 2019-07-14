/** 
 * Cuts prefix if exists.
 */
public static String cutPrefix(String string,final String prefix){
  if (string.startsWith(prefix)) {
    string=string.substring(prefix.length());
  }
  return string;
}
