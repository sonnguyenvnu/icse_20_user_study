@Override public void process(final HttpResponse response,final HttpContext context) throws HttpException, IOException {
  if (context == null) {
    throw new IllegalArgumentException("HTTP context may not be null");
  }
  HttpEntity entity=response.getEntity();
  if (entity != null) {
    Header ceheader=entity.getContentEncoding();
    if (ceheader != null) {
      HeaderElement[] codecs=ceheader.getElements();
      for (int i=0; i < codecs.length; i++) {
        if (codecs[i].getName().equalsIgnoreCase(HeaderFramework.CONTENT_ENCODING_GZIP)) {
          response.setEntity(new GzipDecompressingEntity(response.getEntity()));
          return;
        }
      }
    }
  }
}
