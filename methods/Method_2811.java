/** 
 * ??FileInputStream??InputStream??????????????
 * @param is
 * @return
 * @throws IOException
 */
public static byte[] readBytesFromOtherInputStream(InputStream is) throws IOException {
  ByteArrayOutputStream data=new ByteArrayOutputStream();
  int readBytes;
  byte[] buffer=new byte[Math.max(is.available(),4096)];
  while ((readBytes=is.read(buffer,0,buffer.length)) != -1) {
    data.write(buffer,0,readBytes);
  }
  data.flush();
  return data.toByteArray();
}
