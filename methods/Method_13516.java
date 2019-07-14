@GetMapping("/flow") public List<FlowRule> apiFlow(){
  return FlowRuleManager.getRules();
}
