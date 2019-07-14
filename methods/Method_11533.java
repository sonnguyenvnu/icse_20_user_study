private CloseableHttpClient generateClient(Site site){
  HttpClientBuilder httpClientBuilder=HttpClients.custom();
  httpClientBuilder.setConnectionManager(connectionManager);
  if (site.getUserAgent() != null) {
    httpClientBuilder.setUserAgent(site.getUserAgent());
  }
 else {
    httpClientBuilder.setUserAgent("");
  }
  if (site.isUseGzip()) {
    httpClientBuilder.addInterceptorFirst(new HttpRequestInterceptor(){
      public void process(      final HttpRequest request,      final HttpContext context) throws HttpException, IOException {
        if (!request.containsHeader("Accept-Encoding")) {
          request.addHeader("Accept-Encoding","gzip");
        }
      }
    }
);
  }
  httpClientBuilder.setRedirectStrategy(new CustomRedirectStrategy());
  SocketConfig.Builder socketConfigBuilder=SocketConfig.custom();
  socketConfigBuilder.setSoKeepAlive(true).setTcpNoDelay(true);
  socketConfigBuilder.setSoTimeout(site.getTimeOut());
  SocketConfig socketConfig=socketConfigBuilder.build();
  httpClientBuilder.setDefaultSocketConfig(socketConfig);
  connectionManager.setDefaultSocketConfig(socketConfig);
  httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(site.getRetryTimes(),true));
  generateCookie(httpClientBuilder,site);
  return httpClientBuilder.build();
}
