@Override public Optional<ResponseHandler> getMatched(final RestIdMatcher resourceName,final HttpRequest httpRequest){
  for (  RestSetting setting : settings) {
    RestIdMatcher idMatcher=RestIdMatchers.match(join(resourceName.resourceUri(),this.id.resourceUri(),this.name));
    Optional<ResponseHandler> responseHandler=setting.getMatched(idMatcher,httpRequest);
    if (responseHandler.isPresent()) {
      return responseHandler;
    }
  }
  return Optional.absent();
}
