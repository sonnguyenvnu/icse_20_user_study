protected RestClient getRestClient(HttpHost[] hosts,Configuration config){
  final RestClientBuilder restClientBuilder=getRestClientBuilder(hosts);
  final HttpClientConfigCallback httpClientConfigCallback=getHttpClientConfigCallback(config);
  if (httpClientConfigCallback != null) {
    restClientBuilder.setHttpClientConfigCallback(httpClientConfigCallback);
  }
  final RequestConfigCallback requestConfigCallback=getRequestConfigCallback(config);
  if (requestConfigCallback != null) {
    restClientBuilder.setRequestConfigCallback(requestConfigCallback);
  }
  if (config.has(ElasticSearchIndex.MAX_RETRY_TIMEOUT)) {
    restClientBuilder.setMaxRetryTimeoutMillis(config.get(ElasticSearchIndex.MAX_RETRY_TIMEOUT));
  }
  return restClientBuilder.build();
}
