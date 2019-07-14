@Override public final void writeToResponse(final SessionContext context){
  Request request=context.getRequest();
  Response response=context.getResponse();
  if (HttpRequest.class.isInstance(request) && MutableHttpResponse.class.isInstance(response)) {
    HttpRequest httpRequest=HttpRequest.class.cast(request);
    MutableHttpResponse httpResponse=MutableHttpResponse.class.cast(response);
    doWriteToResponse(httpRequest,httpResponse);
    return;
  }
  MutableResponse mutableResponse=MutableResponse.class.cast(response);
  mutableResponse.setContent(requireResponseContent(request));
}
