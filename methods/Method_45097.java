private String requestProtocolLine(final HttpRequest request){
  return request.getMethod().name() + ' ' + request.getUri() + ' ' + request.getVersion().text();
}
