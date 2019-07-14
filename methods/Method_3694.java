/** 
 * ???????????????
 * @param str
 * @return
 */
public static boolean isDelimiter(String str){
  if (str != null && ("-".equals(str) || "?".equals(str)))   return true;
 else   return false;
}
