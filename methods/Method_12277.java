/** 
 * @param inputPass
 * @return
 */
public static String inputPassFormPass(String inputPass){
  String str="" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
  return md5(str);
}
