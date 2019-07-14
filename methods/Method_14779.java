/** 
 * ??????????string,?null???""
 * @param s
 * @return
 */
public static String getNoBlankString(String s){
  return getString(s).replaceAll("\\s","");
}
