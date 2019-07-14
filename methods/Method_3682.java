/** 
 * ??????
 * @param str
 * @return
 */
public static boolean isAllNum(String str){
  if (str == null)   return false;
  int i=0;
  if ("±+-??—".indexOf(str.charAt(0)) != -1)   i++;
  while (i < str.length() && "??????????".indexOf(str.charAt(i)) != -1)   i++;
  if (i > 0 && i < str.length()) {
    char ch=str.charAt(i);
    if ("·?:?,?.?/".indexOf(ch) != -1) {
      i++;
      while (i < str.length() && "??????????".indexOf(str.charAt(i)) != -1)       i++;
    }
  }
  if (i >= str.length())   return true;
  while (i < str.length() && "0123456789".indexOf(str.charAt(i)) != -1)   i++;
  if (i > 0 && i < str.length()) {
    char ch=str.charAt(i);
    if (',' == ch || '.' == ch || '/' == ch || ':' == ch || "?·???".indexOf(ch) != -1) {
      i++;
      while (i < str.length() && "0123456789".indexOf(str.charAt(i)) != -1)       i++;
    }
  }
  if (i < str.length()) {
    if ("??????%?‰".indexOf(str.charAt(i)) != -1)     i++;
  }
  if (i >= str.length())   return true;
  return false;
}
