/** 
 * Returns a new  {@link String} constructed by decoding UTF-8 encoded bytes.
 * @param bytes The UTF-8 encoded bytes to decode.
 * @return The string.
 */
public static String fromUtf8Bytes(byte[] bytes){
  return new String(bytes,Charset.forName(C.UTF8_NAME));
}
