/** 
 * Cuts sufix if exists.
 */
public static String cutSuffix(String string,final String suffix){
  if (string.endsWith(suffix)) {
    string=string.substring(0,string.length() - suffix.length());
  }
  return string;
}
