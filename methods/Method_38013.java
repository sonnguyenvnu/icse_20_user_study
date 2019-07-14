/** 
 * Appends suffix if doesn't exist.
 */
public static String suffix(String string,final String suffix){
  if (!string.endsWith(suffix)) {
    string+=suffix;
  }
  return string;
}
