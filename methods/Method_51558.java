/** 
 * Parse a rule node as a RuleReference. A RuleReference is a single Rule which comes from another RuleSet with some of it's attributes potentially overridden.
 * @param ruleSetReferenceId The RuleSetReferenceId of the RuleSet being parsed.
 * @param ruleSetBuilder The RuleSet being constructed.
 * @param ruleNode Must be a rule element node.
 * @param ref A reference to a Rule.
 * @param withDeprecatedRuleReferences whether rule references that are deprecated should be ignored or not
 */
private void parseRuleReferenceNode(RuleSetReferenceId ruleSetReferenceId,RuleSetBuilder ruleSetBuilder,Node ruleNode,String ref,boolean withDeprecatedRuleReferences) throws RuleSetNotFoundException {
  Element ruleElement=(Element)ruleNode;
  if (StringUtils.isNotBlank(ruleSetReferenceId.getRuleName()) && !isRuleName(ruleElement,ruleSetReferenceId.getRuleName())) {
    return;
  }
  RuleSetFactory ruleSetFactory=new RuleSetFactory(resourceLoader,RulePriority.LOW,warnDeprecated,this.compatibilityFilter != null);
  boolean isSameRuleSet=false;
  RuleSetReferenceId otherRuleSetReferenceId=RuleSetReferenceId.parse(ref).get(0);
  if (!otherRuleSetReferenceId.isExternal() && containsRule(ruleSetReferenceId,otherRuleSetReferenceId.getRuleName())) {
    otherRuleSetReferenceId=new RuleSetReferenceId(ref,ruleSetReferenceId);
    isSameRuleSet=true;
  }
  Rule referencedRule=ruleSetFactory.createRule(otherRuleSetReferenceId,true);
  if (referencedRule == null) {
    throw new IllegalArgumentException("Unable to find referenced rule " + otherRuleSetReferenceId.getRuleName() + "; perhaps the rule name is mispelled?");
  }
  if (warnDeprecated && referencedRule.isDeprecated()) {
    if (referencedRule instanceof RuleReference) {
      RuleReference ruleReference=(RuleReference)referencedRule;
      if (LOG.isLoggable(Level.WARNING)) {
        LOG.warning("Use Rule name " + ruleReference.getRuleSetReference().getRuleSetFileName() + '/' + ruleReference.getOriginalName() + " instead of the deprecated Rule name " + otherRuleSetReferenceId + ". PMD " + PMDVersion.getNextMajorRelease() + " will remove support for this deprecated Rule name usage.");
      }
    }
 else     if (referencedRule instanceof MockRule) {
      if (LOG.isLoggable(Level.WARNING)) {
        LOG.warning("Discontinue using Rule name " + otherRuleSetReferenceId + " as it has been removed from PMD and no longer functions." + " PMD " + PMDVersion.getNextMajorRelease() + " will remove support for this Rule.");
      }
    }
 else {
      if (LOG.isLoggable(Level.WARNING)) {
        LOG.warning("Discontinue using Rule name " + otherRuleSetReferenceId + " as it is scheduled for removal from PMD." + " PMD " + PMDVersion.getNextMajorRelease() + " will remove support for this Rule.");
      }
    }
  }
  RuleSetReference ruleSetReference=new RuleSetReference(otherRuleSetReferenceId.getRuleSetFileName(),false);
  RuleReference ruleReference=new RuleFactory().decorateRule(referencedRule,ruleSetReference,ruleElement);
  if (warnDeprecated && ruleReference.isDeprecated()) {
    if (LOG.isLoggable(Level.WARNING)) {
      LOG.warning("Use Rule name " + ruleReference.getRuleSetReference().getRuleSetFileName() + '/' + ruleReference.getOriginalName() + " instead of the deprecated Rule name " + ruleSetReferenceId.getRuleSetFileName() + '/' + ruleReference.getName() + ". PMD " + PMDVersion.getNextMajorRelease() + " will remove support for this deprecated Rule name usage.");
    }
  }
  if (withDeprecatedRuleReferences || !isSameRuleSet || !ruleReference.isDeprecated()) {
    ruleSetBuilder.addRuleReplaceIfExists(ruleReference);
  }
}
