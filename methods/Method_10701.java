/** 
 * ??????ASCII?
 * @param s ???????
 * @return ????????1???????ascii??????-1
 */
public static int oneCn2ASCII(String s){
  if (s.length() != 1) {
    return -1;
  }
  int ascii=0;
  try {
    byte[] bytes=s.getBytes("GB2312");
    if (bytes.length == 1) {
      ascii=bytes[0];
    }
 else     if (bytes.length == 2) {
      int highByte=256 + bytes[0];
      int lowByte=256 + bytes[1];
      ascii=(256 * highByte + lowByte) - 256 * 256;
    }
 else {
      throw new IllegalArgumentException("Illegal resource string");
    }
  }
 catch (  UnsupportedEncodingException e) {
    e.printStackTrace();
  }
  return ascii;
}
