@Override protected void doWriteToResponse(final HttpRequest httpRequest,final MutableHttpResponse httpResponse){
  String version=resource.readFor(httpRequest).toString();
  httpResponse.setVersion(HttpProtocolVersion.versionOf(version));
}
