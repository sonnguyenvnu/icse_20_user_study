/** 
 * Recursively resolves rule references until the last reference. The last reference is returned. If the given rule not a reference, the rule is returned.
 * @param rule
 * @return
 */
public static Rule resolveRuleReferences(Rule rule){
  Rule result=rule;
  Rule ref=rule;
  while (ref instanceof RuleReference) {
    result=ref;
    ref=((RuleReference)ref).getRule();
  }
  return result;
}
