/** 
 * ????????
 * @param s
 * @param trim
 * @return
 */
public static boolean isNotEmpty(String s,boolean trim){
  if (s == null) {
    return false;
  }
  if (trim) {
    s=s.trim();
  }
  if (s.length() <= 0) {
    return false;
  }
  currentString=s;
  return true;
}
