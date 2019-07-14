private HttpResponse doForward(final HttpRequest request,final URL remoteUrl){
  try {
    HttpRequestBase remoteRequest=prepareRemoteRequest(request,remoteUrl);
    return setupResponse(request,client.execute(remoteRequest));
  }
 catch (  IOException e) {
    logger.error("Failed to load remote and try to failover",e);
    return failover.failover(request);
  }
 finally {
    try {
      client.close();
    }
 catch (    IOException ignored) {
    }
  }
}
