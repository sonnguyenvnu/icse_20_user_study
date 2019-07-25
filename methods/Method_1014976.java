/** 
 * ????
 * @param key  key
 * @param salt ?
 * @return ???????
 */
public static String sign(String key,String salt){
  return DigestUtils.md5Hex((key + "erp" + salt).getBytes());
}
