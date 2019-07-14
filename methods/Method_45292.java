private Optional<ResponseHandler> getSingleOrAllHandler(final HttpRequest httpRequest,final CompositeRestSetting<RestSingleSetting> single,final CompositeRestSetting<RestAllSetting> all,final RestIdMatcher name){
  Optional<ResponseHandler> singleHandler=single.getMatched(name,httpRequest);
  if (singleHandler.isPresent()) {
    return singleHandler;
  }
  Optional<ResponseHandler> allHandler=all.getMatched(name,httpRequest);
  if (allHandler.isPresent()) {
    return allHandler;
  }
  return absent();
}
