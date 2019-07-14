@GetMapping("/mono") public Mono<String> mono(){
  return Mono.just("simple string").transform(new SentinelReactorTransformer<>("mono"));
}
