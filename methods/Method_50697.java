@SuppressWarnings("rawtypes") @Override protected RuleViolation createRuleViolation(Rule rule,RuleContext ruleContext,Node node,String message){
  return new ApexRuleViolation<>(rule,ruleContext,node,message);
}
