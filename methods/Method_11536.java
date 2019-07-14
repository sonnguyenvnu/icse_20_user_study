private HttpUriRequest convertHttpUriRequest(Request request,Site site,Proxy proxy){
  RequestBuilder requestBuilder=selectRequestMethod(request).setUri(UrlUtils.fixIllegalCharacterInUrl(request.getUrl()));
  if (site.getHeaders() != null) {
    for (    Map.Entry<String,String> headerEntry : site.getHeaders().entrySet()) {
      requestBuilder.addHeader(headerEntry.getKey(),headerEntry.getValue());
    }
  }
  RequestConfig.Builder requestConfigBuilder=RequestConfig.custom();
  if (site != null) {
    requestConfigBuilder.setConnectionRequestTimeout(site.getTimeOut()).setSocketTimeout(site.getTimeOut()).setConnectTimeout(site.getTimeOut()).setCookieSpec(CookieSpecs.STANDARD);
  }
  if (proxy != null) {
    requestConfigBuilder.setProxy(new HttpHost(proxy.getHost(),proxy.getPort()));
  }
  requestBuilder.setConfig(requestConfigBuilder.build());
  HttpUriRequest httpUriRequest=requestBuilder.build();
  if (request.getHeaders() != null && !request.getHeaders().isEmpty()) {
    for (    Map.Entry<String,String> header : request.getHeaders().entrySet()) {
      httpUriRequest.addHeader(header.getKey(),header.getValue());
    }
  }
  return httpUriRequest;
}
