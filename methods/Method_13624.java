private Object convertRule(String ruleStr){
  try {
    final Object rule=objectMapper.readValue(ruleStr,ruleClass);
    RuleType ruleType=RuleType.getByClass(ruleClass).get();
switch (ruleType) {
case FLOW:
      if (!FlowRuleUtil.isValidRule((FlowRule)rule)) {
        return null;
      }
    break;
case DEGRADE:
  if (!DegradeRuleManager.isValidRule((DegradeRule)rule)) {
    return null;
  }
default :
break;
}
return rule;
}
 catch (Exception e) {
}
return null;
}
