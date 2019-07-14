/** 
 * ??????????
 * @param s ?????
 * @return ??
 */
public static String getPYAllFirstLetter(String s){
  if (isNullString(s)) {
    return "";
  }
  String py="";
  for (int i=0; i < s.length(); i++) {
    String first=s.substring(i,i + 1);
    String py1=oneCn2PY(first);
    if (py1 != null) {
      py+=py1.substring(0,1);
    }
  }
  if (py == "") {
    return null;
  }
  return py;
}
