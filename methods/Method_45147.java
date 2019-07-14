public static ResponseHandler responseHandler(final Resource resource){
  if (HANDLERS.containsKey(resource.id())) {
    return createResponseHandler(resource);
  }
  throw new IllegalArgumentException(format("unknown response handler for [%s]",resource.id()));
}
