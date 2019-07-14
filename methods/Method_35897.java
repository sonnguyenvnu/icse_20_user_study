private HttpHeaders headersFrom(HttpResponse httpResponse,ResponseDefinition responseDefinition){
  List<HttpHeader> httpHeaders=new LinkedList<HttpHeader>();
  for (  Header header : httpResponse.getAllHeaders()) {
    httpHeaders.add(new HttpHeader(header.getName(),header.getValue()));
  }
  if (responseDefinition.getHeaders() != null) {
    httpHeaders.addAll(responseDefinition.getHeaders().all());
  }
  return new HttpHeaders(httpHeaders);
}
