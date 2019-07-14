/** 
 * ??json??????
 * @param s
 * @return
 */
public static boolean isJsonCorrect(String s){
  if (s == null || s.equals("") || s.equals("[null]") || s.equals("{null}") || s.equals("null")) {
    return false;
  }
  return true;
}
