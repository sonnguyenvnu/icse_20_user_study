/** 
 * ??salt ?? ????
 * @param inputPass
 * @return
 */
public static String formPassFormPass(String inputPass){
  String str="" + getSalt.charAt(0) + getSalt.charAt(2) + inputPass + getSalt.charAt(4) + getSalt.charAt(6);
  return MD5(str);
}
