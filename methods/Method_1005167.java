/** 
 * ???????????????
 * @param str
 * @return [true]|[false] ??|???
 */
public static boolean check(String str){
  String sIllegal="'\"";
  int len=sIllegal.length();
  if (null == str)   return false;
  for (int i=0; i < len; i++) {
    if (str.indexOf(sIllegal.charAt(i)) != -1)     return true;
  }
  return false;
}
