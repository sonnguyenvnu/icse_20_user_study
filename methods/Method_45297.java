private Optional<ResponseHandler> doGetResponseHandler(final HttpRequest httpRequest){
  HttpMethod method=httpRequest.getMethod();
  if (HttpMethod.GET == method) {
    return getGetHandler(httpRequest);
  }
  if (HttpMethod.POST == method) {
    return getPostHandler(httpRequest);
  }
  if (HttpMethod.PUT == method) {
    return getSingleResponseHandler(putSettings,httpRequest);
  }
  if (HttpMethod.DELETE == method) {
    return getSingleResponseHandler(deleteSettings,httpRequest);
  }
  if (HttpMethod.HEAD == method) {
    return getHeadHandler(httpRequest);
  }
  if (HttpMethod.PATCH == method) {
    return getSingleResponseHandler(patchSettings,httpRequest);
  }
  return absent();
}
