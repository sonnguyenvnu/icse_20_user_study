/** 
 * outputStream?inputStream
 * @param out ???
 * @return inputStream??
 */
public ByteArrayInputStream output2InputStream(OutputStream out){
  if (out == null) {
    return null;
  }
  return new ByteArrayInputStream(((ByteArrayOutputStream)out).toByteArray());
}
