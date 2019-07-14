/** 
 * Encode the specified value.
 * @param value The value to encode.
 * @return The encoded value.
 */
public static String oauthEncode(String value){
  if (value == null) {
    return "";
  }
  try {
    return new String(URLCodec.encodeUrl(SAFE_CHARACTERS,value.getBytes("UTF-8")),"US-ASCII");
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
