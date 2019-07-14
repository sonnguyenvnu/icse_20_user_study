public void bindTo(final SocketServer server){
  if (isAnyResponse()) {
    server.response(getResponseHandler());
    return;
  }
  server.request(getRequestMatcher()).response(getResponseHandler());
}
