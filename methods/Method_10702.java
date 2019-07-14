/** 
 * ????????
 * @param s ???????
 * @return ????????1??????????????{@code null}
 */
public static String oneCn2PY(String s){
  int ascii=oneCn2ASCII(s);
  if (ascii == -1) {
    return null;
  }
  String ret=null;
  if (0 <= ascii && ascii <= 127) {
    ret=String.valueOf((char)ascii);
  }
 else {
    for (int i=pyValue.length - 1; i >= 0; i--) {
      if (pyValue[i] <= ascii) {
        ret=pyStr[i];
        break;
      }
    }
  }
  return ret;
}
