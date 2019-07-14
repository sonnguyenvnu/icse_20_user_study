@Override protected byte[] getResponseData(HttpEntity entity) throws IOException {
  if (entity != null) {
    InputStream instream=entity.getContent();
    long contentLength=entity.getContentLength() + current;
    FileOutputStream buffer=new FileOutputStream(getTargetFile(),append);
    if (instream != null) {
      try {
        byte[] tmp=new byte[BUFFER_SIZE];
        int l;
        while (current < contentLength && (l=instream.read(tmp)) != -1 && !Thread.currentThread().isInterrupted()) {
          current+=l;
          buffer.write(tmp,0,l);
          sendProgressMessage(current,contentLength);
        }
      }
  finally {
        instream.close();
        buffer.flush();
        buffer.close();
      }
    }
  }
  return null;
}
