/** 
 * ????????
 * @param key  key
 * @param salt ?
 * @param sign ??
 * @return ???? true???
 */
public static boolean verify(String key,String salt,String sign){
  return sign.equals(sign(key,salt));
}
