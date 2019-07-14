/** 
 * @param unicode  ???unicode???????????unicode????\u5317\u4eac\u6b22\u8fce\u4f60
 * @return ????????????
 */
public static String getStrFromUnicode(String unicode){
  StringBuffer sb=new StringBuffer();
  String[] hex=unicode.split("\\\\u");
  for (int i=1; i < hex.length; i++) {
    int data=Integer.parseInt(hex[i],16);
    sb.append((char)data);
  }
  return sb.toString();
}
