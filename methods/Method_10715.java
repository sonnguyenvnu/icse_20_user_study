/** 
 * outputStream?byteArr
 * @param out ???
 * @return ????
 */
public static byte[] outputStream2Bytes(OutputStream out){
  if (out == null) {
    return null;
  }
  return ((ByteArrayOutputStream)out).toByteArray();
}
