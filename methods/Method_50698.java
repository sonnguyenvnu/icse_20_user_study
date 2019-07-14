@Override @SuppressWarnings("rawtypes") protected RuleViolation createRuleViolation(Rule rule,RuleContext ruleContext,Node node,String message,int beginLine,int endLine){
  return new ApexRuleViolation(rule,ruleContext,node,message,beginLine,endLine);
}
