/** 
 * Decode the given String to UTF-8.
 * @param content
 * @return
 */
public static String decode(String s){
  try {
    return URLDecoder.decode(s,EncodingConstants.UTF_8.name());
  }
 catch (  UnsupportedEncodingException e) {
    logger.severe("String - exception: " + e);
    return s;
  }
}
