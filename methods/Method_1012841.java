/** 
 * decode the "." to Hex
 * @param src
 * @return
 */
public static String dotdecod(String src){
  StringBuilder sb=new StringBuilder(src.length());
  char[] chars=src.toCharArray();
  for (int i=0; i < chars.length; i++) {
    if (chars[i] == '.') {
      sb.append("/u" + Integer.toHexString(chars[i]));
    }
 else {
      sb.append(chars[i]);
    }
  }
  return sb.toString();
}
