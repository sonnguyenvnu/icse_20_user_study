@Bean public CheckTokenEndpoint checkTokenEndpoint(){
  CheckTokenEndpoint endpoint=new CheckTokenEndpoint(getEndpointsConfigurer().getResourceServerTokenServices());
  endpoint.setAccessTokenConverter(getEndpointsConfigurer().getAccessTokenConverter());
  endpoint.setExceptionTranslator(exceptionTranslator());
  return endpoint;
}
