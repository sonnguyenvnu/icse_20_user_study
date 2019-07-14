private RuleSet getLastRuleSet(){
  if (iRuleSets.size() == 0) {
    addCutover(Integer.MIN_VALUE,'w',1,1,0,false,0);
  }
  return iRuleSets.get(iRuleSets.size() - 1);
}
