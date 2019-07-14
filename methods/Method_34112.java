protected RestOperations getRestTemplate(){
  if (restTemplate == null) {
synchronized (this) {
      if (restTemplate == null) {
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.setErrorHandler(getResponseErrorHandler());
        restTemplate.setRequestFactory(requestFactory);
        restTemplate.setInterceptors(interceptors);
        this.restTemplate=restTemplate;
      }
    }
  }
  if (messageConverters == null) {
    setMessageConverters(new RestTemplate().getMessageConverters());
  }
  return restTemplate;
}
