@Override protected RuleViolation createRuleViolation(final Rule rule,final RuleContext ruleContext,final Node node,final String message){
  return new ParametricRuleViolation<>(rule,ruleContext,(AbstractVmNode)node,message);
}
