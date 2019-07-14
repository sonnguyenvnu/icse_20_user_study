public static HttpUriRequest getHttpRequestFor(ResponseDefinition response){
  final RequestMethod method=response.getOriginalRequest().getMethod();
  final String url=response.getProxyUrl();
  return HttpClientFactory.getHttpRequestFor(method,url);
}
