private HttpRequestBase prepareRemoteRequest(final HttpRequest request,final URL remoteUrl){
  FullHttpRequest httpRequest=((DefaultHttpRequest)request).toFullHttpRequest();
  return prepareRemoteRequest(httpRequest,remoteUrl);
}
