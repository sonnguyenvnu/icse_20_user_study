/** 
 * @todo ??????????????
 * @param compareStr
 * @param compareAry
 * @param ignoreCase
 * @return
 */
public static boolean any(String compareStr,String[] compareAry,boolean ignoreCase){
  if (compareStr == null || (compareAry == null || compareAry.length == 0))   return false;
  for (  String s : compareAry) {
    if (ignoreCase) {
      if (compareStr.equalsIgnoreCase(s))       return true;
    }
 else     if (compareStr.equals(s))     return true;
  }
  return false;
}
