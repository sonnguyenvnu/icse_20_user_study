private ResponseHandler getResponseHandler(final FluentIterable<ResponseHandler> handlers){
  if (handlers.size() == 1) {
    return handlers.get(0);
  }
  return and(handlers);
}
