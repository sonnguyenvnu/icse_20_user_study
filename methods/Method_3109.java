/** 
 * ??????????
 * @param pinyinStringArray
 * @return
 */
public static boolean valid(String[] pinyinStringArray){
  for (  String p : pinyinStringArray) {
    if (!valid(p))     return false;
  }
  return true;
}
