/** 
 * ??????(????????? {@link #humpToLine(String)})
 * @param str
 * @return
 */
public static String humpToLine2(String str){
  return str.replaceAll("[A-Z]","_$0").toLowerCase();
}
