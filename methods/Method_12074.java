/** 
 * ???md5--??? ?????? ---?salt?????
 * @param formPass
 * @param salt
 * @return
 */
public static String formPassToDBPass(String formPass,String salt){
  String str="" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(4) + salt.charAt(6);
  return MD5(str);
}
