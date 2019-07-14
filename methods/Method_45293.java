private Optional<ResponseHandler> getHeadHandler(final HttpRequest httpRequest){
  Optional<ResponseHandler> handler=getSingleOrAllHandler(httpRequest,headSettings,headAllSettings,name);
  if (handler.isPresent()) {
    return handler;
  }
  return of(NOT_FOUND_HANDLER);
}
