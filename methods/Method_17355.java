public Policy makePolicy(){
  Set<Policy> policies=Registry.policy(settings,policyName);
  if (policies.size() > 1) {
    throw new IllegalArgumentException("Use one variation per policy configuration: " + policies.stream().map(policy -> policy.stats().name()).collect(toList()));
  }
  return policies.iterator().next();
}
