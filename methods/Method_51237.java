/** 
 * Initialize the RuleChainVisitor to be ready to perform visitations. This method should not be called until it is known that all Rules participating in the RuleChain are ready to be initialized themselves. Some rules may require full initialization to determine if they will participate in the RuleChain, so this has been delayed as long as possible to ensure that manipulation of the Rules is no longer occurring.
 */
protected void initialize(){
  if (nodeNameToNodes != null) {
    return;
  }
  Set<String> visitedNodes=new HashSet<>();
  for (Iterator<Map.Entry<RuleSet,List<Rule>>> entryIterator=ruleSetRules.entrySet().iterator(); entryIterator.hasNext(); ) {
    Map.Entry<RuleSet,List<Rule>> entry=entryIterator.next();
    for (Iterator<Rule> ruleIterator=entry.getValue().iterator(); ruleIterator.hasNext(); ) {
      Rule rule=ruleIterator.next();
      if (rule.isRuleChain()) {
        visitedNodes.addAll(rule.getRuleChainVisits());
      }
 else {
        ruleIterator.remove();
      }
    }
    if (entry.getValue().isEmpty()) {
      entryIterator.remove();
    }
  }
  nodeNameToNodes=new HashMap<>();
  for (  String s : visitedNodes) {
    List<Node> nodes=new ArrayList<>(100);
    nodeNameToNodes.put(s,nodes);
  }
}
