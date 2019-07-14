/** 
 * Returns byte array of response HttpEntity contents
 * @param entity can be null
 * @return response entity body or null
 * @throws java.io.IOException if reading entity or creating byte array failed
 */
byte[] getResponseData(HttpEntity entity) throws IOException {
  byte[] responseBody=null;
  if (entity != null) {
    InputStream instream=entity.getContent();
    if (instream != null) {
      long contentLength=entity.getContentLength();
      if (contentLength > Integer.MAX_VALUE) {
        throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
      }
      int buffersize=(contentLength <= 0) ? BUFFER_SIZE : (int)contentLength;
      try {
        ByteArrayBuffer buffer=new ByteArrayBuffer(buffersize);
        try {
          byte[] tmp=new byte[BUFFER_SIZE];
          long count=0;
          int l;
          while ((l=instream.read(tmp)) != -1 && !Thread.currentThread().isInterrupted()) {
            count+=l;
            buffer.append(tmp,0,l);
            sendProgressMessage(count,(contentLength <= 0 ? 1 : contentLength));
          }
        }
  finally {
          AsyncHttpClient.silentCloseInputStream(instream);
          AsyncHttpClient.endEntityViaReflection(entity);
        }
        responseBody=buffer.toByteArray();
      }
 catch (      OutOfMemoryError e) {
        System.gc();
        throw new IOException("File too large to fit into available memory");
      }
    }
  }
  return responseBody;
}
