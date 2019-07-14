/** 
 * Utility method for decoding a base-64-encoded string.
 * @param value The base-64-encoded string.
 * @return The decoded value.
 */
private static byte[] base64Decode(String value){
  if (value == null) {
    return null;
  }
  try {
    return Base64.decodeBase64(value.getBytes("UTF-8"));
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
