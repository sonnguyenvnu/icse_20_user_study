@Override public final Optional<ResponseHandler> getMatched(final RestIdMatcher resourceName,final HttpRequest httpRequest){
  if (getRequestMatcher(resourceName).match(httpRequest)) {
    return of(handler);
  }
  return absent();
}
