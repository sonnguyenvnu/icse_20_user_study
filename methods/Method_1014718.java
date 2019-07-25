/** 
 * unzip.
 * @param bytes compressed byte array.
 * @return byte uncompressed array.
 * @throws IOException
 */
public static byte[] unzip(byte[] bytes) throws IOException {
  UnsafeByteArrayInputStream bis=new UnsafeByteArrayInputStream(bytes);
  UnsafeByteArrayOutputStream bos=new UnsafeByteArrayOutputStream();
  InputStream is=new InflaterInputStream(bis);
  try {
    IOUtils.write(is,bos);
    return bos.toByteArray();
  }
  finally {
    is.close();
    bis.close();
    bos.close();
  }
}
