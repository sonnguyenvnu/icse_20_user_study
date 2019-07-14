@Override protected final MessageContent responseContent(final Request request){
  if (!HttpRequest.class.isInstance(request)) {
    throw new MocoException("Only HTTP request is allowed");
  }
  return responseContent((HttpRequest)request);
}
