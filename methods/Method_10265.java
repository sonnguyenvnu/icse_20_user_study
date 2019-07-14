/** 
 * Checks the InputStream if it contains  GZIP compressed data
 * @param inputStream InputStream to be checked
 * @return true or false if the stream contains GZIP compressed data
 * @throws java.io.IOException if read from inputStream fails
 */
public static boolean isInputStreamGZIPCompressed(final PushbackInputStream inputStream) throws IOException {
  if (inputStream == null)   return false;
  byte[] signature=new byte[2];
  int count=0;
  try {
    while (count < 2) {
      int readCount=inputStream.read(signature,count,2 - count);
      if (readCount < 0)       return false;
      count=count + readCount;
    }
  }
  finally {
    inputStream.unread(signature,0,count);
  }
  int streamHeader=((int)signature[0] & 0xff) | ((signature[1] << 8) & 0xff00);
  return GZIPInputStream.GZIP_MAGIC == streamHeader;
}
