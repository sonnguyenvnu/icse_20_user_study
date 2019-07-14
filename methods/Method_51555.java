/** 
 * Parse a rule node.
 * @param ruleSetReferenceId The RuleSetReferenceId of the RuleSet being parsed.
 * @param ruleSetBuilder The RuleSet being constructed.
 * @param ruleNode Must be a rule element node.
 * @param withDeprecatedRuleReferences whether rule references that are deprecated should be ignored or not
 */
private void parseRuleNode(RuleSetReferenceId ruleSetReferenceId,RuleSetBuilder ruleSetBuilder,Node ruleNode,boolean withDeprecatedRuleReferences) throws ClassNotFoundException, InstantiationException, IllegalAccessException, RuleSetNotFoundException {
  Element ruleElement=(Element)ruleNode;
  String ref=ruleElement.getAttribute("ref");
  if (ref.endsWith("xml")) {
    parseRuleSetReferenceNode(ruleSetBuilder,ruleElement,ref);
  }
 else   if (StringUtils.isBlank(ref)) {
    parseSingleRuleNode(ruleSetReferenceId,ruleSetBuilder,ruleNode);
  }
 else {
    parseRuleReferenceNode(ruleSetReferenceId,ruleSetBuilder,ruleNode,ref,withDeprecatedRuleReferences);
  }
}
