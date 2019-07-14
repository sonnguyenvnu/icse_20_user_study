/** 
 * ???md5?salt?????
 * @param formPass
 * @param salt
 * @return
 */
public static String formPassToDBPass(String formPass,String salt){
  String str="" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
  return md5(str);
}
