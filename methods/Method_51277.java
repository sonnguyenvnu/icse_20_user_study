@Override public List<String> getRuleChainVisits(){
  if (xPathRuleQueryNeedsInitialization()) {
    initXPathRuleQuery();
    for (    String nodeName : xpathRuleQuery.getRuleChainVisits()) {
      super.addRuleChainVisit(nodeName);
    }
  }
  return super.getRuleChainVisits();
}
