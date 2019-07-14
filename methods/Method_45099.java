private String responseProtocolLine(final HttpResponse httpResponse){
  return httpResponse.getVersion().text() + ' ' + httpResponse.getStatus();
}
