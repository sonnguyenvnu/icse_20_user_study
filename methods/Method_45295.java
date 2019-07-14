public Optional<ResponseHandler> getResponseHandler(final HttpRequest httpRequest){
  if (allMatcher.match(httpRequest) || this.singleMatcher.match(httpRequest)) {
    return doGetResponseHandler(httpRequest);
  }
  return getSubResponseHandler(httpRequest);
}
