/** 
 * Creates a new rulesets with the given string. The resulting rulesets will contain all referenced rulesets.
 * @param rulesets the string with the rulesets to load
 * @param factory the ruleset factory
 * @return the rulesets
 * @throws IllegalArgumentException if rulesets is empty (means, no rules have been found) or if a ruleset couldn't be found.
 */
public static RuleSets getRuleSets(String rulesets,RuleSetFactory factory){
  RuleSets ruleSets=null;
  try {
    ruleSets=factory.createRuleSets(rulesets);
    printRuleNamesInDebug(ruleSets);
    if (ruleSets.ruleCount() == 0) {
      String msg="No rules found. Maybe you mispelled a rule name? (" + rulesets + ')';
      LOG.log(Level.SEVERE,msg);
      throw new IllegalArgumentException(msg);
    }
  }
 catch (  RuleSetNotFoundException rsnfe) {
    LOG.log(Level.SEVERE,"Ruleset not found",rsnfe);
    throw new IllegalArgumentException(rsnfe);
  }
  return ruleSets;
}
