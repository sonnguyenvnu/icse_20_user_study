@Override protected byte[] getResponseData(HttpEntity entity) throws IOException {
  if (entity != null) {
    InputStream instream=entity.getContent();
    long contentLength=entity.getContentLength();
    FileOutputStream buffer=new FileOutputStream(getTargetFile(),this.append);
    if (instream != null) {
      try {
        byte[] tmp=new byte[BUFFER_SIZE];
        int l, count=0;
        while ((l=instream.read(tmp)) != -1 && !Thread.currentThread().isInterrupted()) {
          count+=l;
          buffer.write(tmp,0,l);
          sendProgressMessage(count,contentLength);
        }
      }
  finally {
        AsyncHttpClient.silentCloseInputStream(instream);
        buffer.flush();
        AsyncHttpClient.silentCloseOutputStream(buffer);
      }
    }
  }
  return null;
}
