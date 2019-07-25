@Override public void process(final HttpRequest request,final HttpContext context) throws HttpException, IOException {
  if (!request.containsHeader(HeaderFramework.ACCEPT_ENCODING)) {
    request.addHeader(HeaderFramework.ACCEPT_ENCODING,HeaderFramework.CONTENT_ENCODING_GZIP);
  }
}
