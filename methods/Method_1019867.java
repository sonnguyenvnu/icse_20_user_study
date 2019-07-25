@RequestMapping("/api") public Map<String,Object> test() throws ExecutionException, InterruptedException {
  RestTemplate restTemplateApi=SofaTracerRestTemplateBuilder.buildRestTemplate();
  ResponseEntity<String> responseEntity=restTemplateApi.getForEntity("http://sac.alipay.net:8080/rest",String.class);
  logger.info("Response is {}",responseEntity.getBody());
  AsyncRestTemplate asyncRestTemplate=SofaTracerRestTemplateBuilder.buildAsyncRestTemplate();
  ListenableFuture<ResponseEntity<String>> forEntity=asyncRestTemplate.getForEntity("http://sac.alipay.net:8080/asyncrest",String.class);
  logger.info("Async Response is {}",forEntity.get().getBody());
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("count",counter.incrementAndGet());
  return map;
}
