@Override public void addViolation(RuleContext ruleContext,Rule rule,Node node,String message,Object[] args){
  String formattedMessage=cleanup(message,args);
  ruleContext.getReport().addRuleViolation(createRuleViolation(rule,ruleContext,node,formattedMessage));
}
