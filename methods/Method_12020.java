private Statement withRules(FrameworkMethod method,Object target,Statement statement){
  RuleContainer ruleContainer=new RuleContainer();
  CURRENT_RULE_CONTAINER.set(ruleContainer);
  try {
    List<TestRule> testRules=getTestRules(target);
    for (    MethodRule each : rules(target)) {
      if (!(each instanceof TestRule && testRules.contains(each))) {
        ruleContainer.add(each);
      }
    }
    for (    TestRule rule : testRules) {
      ruleContainer.add(rule);
    }
  }
  finally {
    CURRENT_RULE_CONTAINER.remove();
  }
  return ruleContainer.apply(method,describeChild(method),target,statement);
}
