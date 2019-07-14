@Override protected void doWriteToResponse(final HttpRequest httpRequest,final MutableHttpResponse httpResponse){
  String value=resource.readFor(httpRequest).toString();
  httpResponse.addHeader(name,value);
}
