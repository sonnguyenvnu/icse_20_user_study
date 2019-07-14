private void sendBodyWithCorrectEncoding(OutputStream outputStream,long pending) throws IOException {
  if (useGzipWhenAccepted()) {
    GZIPOutputStream gzipOutputStream=null;
    try {
      gzipOutputStream=new GZIPOutputStream(outputStream);
    }
 catch (    Exception e) {
      if (this.data != null) {
        this.data.close();
      }
    }
    if (gzipOutputStream != null) {
      sendBody(gzipOutputStream,-1);
      gzipOutputStream.finish();
    }
  }
 else {
    sendBody(outputStream,pending);
  }
}
