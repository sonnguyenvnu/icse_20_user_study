/** 
 * ???????
 * @param str
 * @return
 */
public static boolean isAllSingleByte(String str){
  assert str != null;
  for (int i=0; i < str.length(); i++) {
    if (str.charAt(i) > 128) {
      return false;
    }
  }
  return true;
}
