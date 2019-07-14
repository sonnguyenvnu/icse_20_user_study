/** 
 * ??response String??????
 * @param is
 * @param charset
 * @return
 * @throws IOException
 */
private static String getResponseStringData(InputStream is,String charset) throws IOException {
  ByteArrayOutputStream respDataStream=new ByteArrayOutputStream();
  String result;
  try {
    byte[] b=new byte[8192];
    int len;
    while (true) {
      len=is.read(b);
      if (len <= 0) {
        is.close();
        break;
      }
      respDataStream.write(b,0,len);
    }
    result=respDataStream.toString(charset);
  }
  finally {
    respDataStream.close();
  }
  return result;
}
