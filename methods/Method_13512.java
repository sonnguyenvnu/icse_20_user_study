@GetMapping("/flow") public Mono<List<FlowRule>> apiFlow(){
  return Mono.just(FlowRuleManager.getRules());
}
