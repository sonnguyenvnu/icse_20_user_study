/** 
 * ?????
 * @param s ?????
 * @return ??
 */
public static String cn2PY(String s){
  String hz, py;
  StringBuilder sb=new StringBuilder();
  for (int i=0; i < s.length(); i++) {
    hz=s.substring(i,i + 1);
    py=oneCn2PY(hz);
    if (py == null) {
      py="?";
    }
    sb.append(py);
  }
  return sb.toString();
}
