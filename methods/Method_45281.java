@Override public Optional<ResponseHandler> getMatched(final RestIdMatcher resourceName,final HttpRequest httpRequest){
  for (  RestSetting setting : settings) {
    Optional<ResponseHandler> responseHandler=setting.getMatched(resourceName,httpRequest);
    if (responseHandler.isPresent()) {
      return responseHandler;
    }
  }
  return Optional.absent();
}
