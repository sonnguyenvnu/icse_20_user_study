private static void addBodyIfPostPutOrPatch(HttpRequest httpRequest,ResponseDefinition response) throws UnsupportedEncodingException {
  Request originalRequest=response.getOriginalRequest();
  if (originalRequest.getMethod().isOneOf(PUT,POST,PATCH)) {
    HttpEntityEnclosingRequest requestWithEntity=(HttpEntityEnclosingRequest)httpRequest;
    requestWithEntity.setEntity(buildEntityFrom(originalRequest));
  }
}
