/** 
 * Parse a rule node as a single Rule. The Rule has been fully defined within the context of the current RuleSet.
 * @param ruleSetReferenceId The RuleSetReferenceId of the RuleSet being parsed.
 * @param ruleSetBuilder The RuleSet being constructed.
 * @param ruleNode Must be a rule element node.
 */
private void parseSingleRuleNode(RuleSetReferenceId ruleSetReferenceId,RuleSetBuilder ruleSetBuilder,Node ruleNode) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
  Element ruleElement=(Element)ruleNode;
  if (StringUtils.isNotBlank(ruleSetReferenceId.getRuleName()) && !isRuleName(ruleElement,ruleSetReferenceId.getRuleName())) {
    return;
  }
  Rule rule=new RuleFactory().buildRule(ruleElement);
  rule.setRuleSetName(ruleSetBuilder.getName());
  ruleSetBuilder.addRule(rule);
}
