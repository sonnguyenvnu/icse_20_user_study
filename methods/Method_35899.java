private void addRequestHeaders(HttpRequest httpRequest,ResponseDefinition response){
  Request originalRequest=response.getOriginalRequest();
  for (  String key : originalRequest.getAllHeaderKeys()) {
    if (headerShouldBeTransferred(key)) {
      if (!HOST_HEADER.equalsIgnoreCase(key) || preserveHostHeader) {
        List<String> values=originalRequest.header(key).values();
        for (        String value : values) {
          httpRequest.addHeader(key,value);
        }
      }
 else {
        if (hostHeaderValue != null) {
          httpRequest.addHeader(key,hostHeaderValue);
        }
 else         if (response.getProxyBaseUrl() != null) {
          httpRequest.addHeader(key,URI.create(response.getProxyBaseUrl()).getAuthority());
        }
      }
    }
  }
  if (response.getAdditionalProxyRequestHeaders() != null) {
    for (    String key : response.getAdditionalProxyRequestHeaders().keys()) {
      httpRequest.setHeader(key,response.getAdditionalProxyRequestHeaders().getHeader(key).firstValue());
    }
  }
}
