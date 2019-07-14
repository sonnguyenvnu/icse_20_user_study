private String getRuleSetKeywords(RuleSet ruleset){
  List<String> ruleNames=new LinkedList<>();
  for (  Rule rule : ruleset.getRules()) {
    ruleNames.add(rule.getName());
  }
  return ruleset.getName() + ", " + StringUtils.join(ruleNames,", ");
}
