@Override protected void doWriteToResponse(final HttpRequest httpRequest,final MutableHttpResponse httpResponse){
  Optional<ResponseHandler> responseHandler=dispatcher.getResponseHandler(httpRequest);
  if (responseHandler.isPresent()) {
    responseHandler.get().writeToResponse(new SessionContext(httpRequest,httpResponse));
    return;
  }
  throw new UnsupportedOperationException("Unsupported REST request");
}
