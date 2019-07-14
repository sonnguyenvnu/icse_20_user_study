/** 
 * ????????
 * @param length
 * @return
 */
private static String createNoncestr(int length){
  StringBuilder sb=new StringBuilder();
  Random rd=new Random();
  int clength=chars.length();
  for (int i=0; i < length; i++) {
    sb.append(chars.charAt(rd.nextInt(clength - 1)));
  }
  return sb.toString();
}
