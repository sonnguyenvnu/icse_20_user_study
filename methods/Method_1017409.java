@Override public HttpHeaders head(String url,URLParameter... parameters) throws HttpClientException {
  return execute(HttpMethod.HEAD,url,parameters).getHeaders();
}
