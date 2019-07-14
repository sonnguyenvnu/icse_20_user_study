@Bean @Scope(value="request",proxyMode=ScopedProxyMode.INTERFACES) protected AccessTokenRequest accessTokenRequest(@Value("#{request.parameterMap}") Map<String,String[]> parameters,@Value("#{request.getAttribute('currentUri')}") String currentUri){
  DefaultAccessTokenRequest request=new DefaultAccessTokenRequest(parameters);
  request.setCurrentUri(currentUri);
  return request;
}
