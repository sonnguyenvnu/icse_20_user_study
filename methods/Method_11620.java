/** 
 * @param url url
 * @return new url
 * @deprecated
 */
public static String encodeIllegalCharacterInUrl(String url){
  return url.replace(" ","%20");
}
