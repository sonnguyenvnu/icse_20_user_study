/** 
 * Returns a new  {@link String} constructed by decoding UTF-8 encoded bytes in a subarray.
 * @param bytes The UTF-8 encoded bytes to decode.
 * @param offset The index of the first byte to decode.
 * @param length The number of bytes to decode.
 * @return The string.
 */
public static String fromUtf8Bytes(byte[] bytes,int offset,int length){
  return new String(bytes,offset,length,Charset.forName(C.UTF8_NAME));
}
