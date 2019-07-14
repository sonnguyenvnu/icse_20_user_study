@Override protected void doWriteToResponse(final HttpRequest httpRequest,final MutableHttpResponse httpResponse){
  String value=header.getValue().readFor(httpRequest).toString();
  httpResponse.addHeader(header.getName(),value);
}
