@Override public void customize(RestTemplate restTemplate){
  List<ClientHttpRequestInterceptor> interceptors=new ArrayList<>();
  RestTemplateInterceptor restTemplateInterceptor=new RestTemplateInterceptor(SofaTracerRestTemplateBuilder.getRestTemplateTracer());
  interceptors.add(restTemplateInterceptor);
  restTemplate.setInterceptors(interceptors);
}
