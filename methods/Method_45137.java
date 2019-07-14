public final boolean hasContentType(final HttpResponse httpResponse){
  return hasHeader(httpResponse,HttpHeaders.CONTENT_TYPE);
}
