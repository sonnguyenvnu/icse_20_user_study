/** 
 * Parse a rule node as an RuleSetReference for all Rules. Every Rule from the referred to RuleSet will be added as a RuleReference except for those explicitly excluded, below the minimum priority threshold for this RuleSetFactory, or which are deprecated.
 * @param ruleSetBuilder The RuleSet being constructed.
 * @param ruleElement Must be a rule element node.
 * @param ref The RuleSet reference.
 */
private void parseRuleSetReferenceNode(RuleSetBuilder ruleSetBuilder,Element ruleElement,String ref) throws RuleSetNotFoundException {
  String priority=null;
  NodeList childNodes=ruleElement.getChildNodes();
  Set<String> excludedRulesCheck=new HashSet<>();
  for (int i=0; i < childNodes.getLength(); i++) {
    Node child=childNodes.item(i);
    if (isElementNode(child,"exclude")) {
      Element excludeElement=(Element)child;
      String excludedRuleName=excludeElement.getAttribute("name");
      excludedRulesCheck.add(excludedRuleName);
    }
 else     if (isElementNode(child,PRIORITY)) {
      priority=parseTextNode(child).trim();
    }
  }
  final RuleSetReference ruleSetReference=new RuleSetReference(ref,true,excludedRulesCheck);
  RuleSetFactory ruleSetFactory=new RuleSetFactory(resourceLoader,RulePriority.LOW,warnDeprecated,this.compatibilityFilter != null);
  RuleSet otherRuleSet=ruleSetFactory.createRuleSet(RuleSetReferenceId.parse(ref).get(0));
  List<RuleReference> potentialRules=new ArrayList<>();
  int countDeprecated=0;
  for (  Rule rule : otherRuleSet.getRules()) {
    excludedRulesCheck.remove(rule.getName());
    if (!ruleSetReference.getExcludes().contains(rule.getName())) {
      RuleReference ruleReference=new RuleReference(rule,ruleSetReference);
      if (priority != null) {
        ruleReference.setPriority(RulePriority.valueOf(Integer.parseInt(priority)));
      }
      if (rule.isDeprecated()) {
        countDeprecated++;
      }
      potentialRules.add(ruleReference);
    }
  }
  boolean rulesetDeprecated=false;
  if (!potentialRules.isEmpty() && potentialRules.size() == countDeprecated) {
    rulesetDeprecated=true;
    LOG.warning("The RuleSet " + ref + " has been deprecated and will be removed in PMD " + PMDVersion.getNextMajorRelease());
  }
  for (  RuleReference r : potentialRules) {
    if (rulesetDeprecated || !r.getRule().isDeprecated()) {
      ruleSetBuilder.addRuleIfNotExists(r);
    }
  }
  if (!excludedRulesCheck.isEmpty()) {
    throw new IllegalArgumentException("Unable to exclude rules " + excludedRulesCheck + "; perhaps the rule name is mispelled?");
  }
}
