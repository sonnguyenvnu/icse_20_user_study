/** 
 * ?????????.
 * @param str ??
 * @param charset ????GBK?
 * @return the int
 */
public static int strlen(String str,String charset){
  if (str == null || str.length() == 0) {
    return 0;
  }
  int length=0;
  try {
    length=str.getBytes(charset).length;
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return length;
}
