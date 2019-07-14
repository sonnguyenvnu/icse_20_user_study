/** 
 * ?????????????
 * @param str
 * @return
 */
public static boolean isSBCCase(String str){
  if (str != null) {
    str+=" ";
    for (int i=0; i < str.length(); i++) {
      String s=str.substring(i,i + 1);
      int length=0;
      try {
        length=s.getBytes("GBK").length;
      }
 catch (      UnsupportedEncodingException e) {
        e.printStackTrace();
        length=s.getBytes().length;
      }
      if (length != 2)       return false;
    }
    return true;
  }
  return false;
}
