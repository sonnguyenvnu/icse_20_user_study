@GetMapping("/gateway") public Mono<Set<GatewayFlowRule>> apiGateway(){
  return Mono.just(GatewayRuleManager.getRules());
}
