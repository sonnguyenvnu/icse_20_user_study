/** 
 * ??????????
 * @param s ???????
 * @return ??
 */
public static String getPYFirstLetter(String s){
  if (isNullString(s)) {
    return "";
  }
  String first, py;
  first=s.substring(0,1);
  py=oneCn2PY(first);
  if (py == null) {
    return null;
  }
  return py.substring(0,1);
}
