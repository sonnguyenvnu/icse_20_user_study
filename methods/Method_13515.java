@GetMapping("/gateway") public Set<GatewayFlowRule> apiGateway(){
  return GatewayRuleManager.getRules();
}
