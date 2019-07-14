private Optional<ResponseHandler> getSingleResponseHandler(final CompositeRestSetting<RestSingleSetting> settings,final HttpRequest httpRequest){
  Optional<ResponseHandler> handler=settings.getMatched(name,httpRequest);
  if (handler.isPresent()) {
    return handler;
  }
  return of(NOT_FOUND_HANDLER);
}
