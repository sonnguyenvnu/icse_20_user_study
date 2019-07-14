/** 
 * ?????
 * @param text ????????
 * @param key ??
 * @param input_charset ????
 * @return ????
 */
public static String sign(String text,String key,String input_charset){
  text=text + key;
  return DigestUtils.md5Hex(getContentBytes(text,input_charset));
}
