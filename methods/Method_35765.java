@Override public ServeEvent serveStubFor(Request request){
  ServeEvent serveEvent=stubMappings.serveFor(request);
  if (serveEvent.isNoExactMatch()) {
    LoggedRequest loggedRequest=LoggedRequest.createFrom(request);
    if (request.isBrowserProxyRequest() && browserProxyingEnabled) {
      return ServeEvent.of(loggedRequest,ResponseDefinition.browserProxy(request));
    }
    logUnmatchedRequest(loggedRequest);
  }
  return serveEvent;
}
