/** 
 * inputStream?byteArr
 * @param is ???
 * @return ????
 */
public static byte[] inputStream2Bytes(InputStream is){
  return input2OutputStream(is).toByteArray();
}
