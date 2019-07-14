private FullHttpResponse handleRequest(final FullHttpRequest message){
  HttpRequest request=DefaultHttpRequest.newRequest(message);
  DefaultMutableHttpResponse httpResponse=getHttpResponse(request);
  FullHttpResponse response=httpResponse.toFullResponse();
  prepareForKeepAlive(message,response);
  monitor.onMessageLeave(httpResponse);
  return response;
}
