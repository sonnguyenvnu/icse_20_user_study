private HttpRequestBase prepareRemoteRequest(final FullHttpRequest request,final URL url){
  HttpRequestBase remoteRequest=createRemoteRequest(request,url);
  remoteRequest.setConfig(createRequestConfig());
  long contentLength=HttpUtil.getContentLength(request,-1);
  if (contentLength > 0 && remoteRequest instanceof HttpEntityEnclosingRequest) {
    HttpEntityEnclosingRequest entityRequest=(HttpEntityEnclosingRequest)remoteRequest;
    entityRequest.setEntity(createEntity(request.content(),contentLength));
  }
  return remoteRequest;
}
