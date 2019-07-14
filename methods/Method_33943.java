/** 
 * Decode the specified value.
 * @param value The value to decode.
 * @return The decoded value.
 * @throws DecoderException when URLCodec fails
 */
public static String oauthDecode(String value) throws DecoderException {
  if (value == null) {
    return "";
  }
  try {
    return new String(URLCodec.decodeUrl(value.getBytes("US-ASCII")),"UTF-8");
  }
 catch (  UnsupportedEncodingException e) {
    throw new RuntimeException(e);
  }
}
