private Optional<ResponseHandler> getSubResponseHandler(final HttpRequest httpRequest){
  for (  SubResourceSetting subResourceSetting : subResourceSettings) {
    Optional<ResponseHandler> matched=subResourceSetting.getMatched(name,httpRequest);
    if (matched.isPresent()) {
      return matched;
    }
  }
  return absent();
}
