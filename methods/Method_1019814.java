public void customize(RestTemplate restTemplate){
  if (!this.gzipped || restTemplate == null) {
    return;
  }
  restTemplate.getInterceptors().add(0,new GzipInterceptor());
}
