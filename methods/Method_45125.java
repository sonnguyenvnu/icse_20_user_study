private HttpResponse setupResponse(final HttpRequest request,final org.apache.http.HttpResponse remoteResponse) throws IOException {
  if (failover.shouldFailover(remoteResponse)) {
    return failover.failover(request);
  }
  HttpResponse httpResponse=setupNormalResponse(remoteResponse);
  failover.onCompleteResponse(request,httpResponse);
  return httpResponse;
}
