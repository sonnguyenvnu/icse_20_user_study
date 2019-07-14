/** 
 * Returns a new byte array containing the code points of a  {@link String} encoded using UTF-8.
 * @param value The {@link String} whose bytes should be obtained.
 * @return The code points encoding using UTF-8.
 */
public static byte[] getUtf8Bytes(String value){
  return value.getBytes(Charset.forName(C.UTF8_NAME));
}
