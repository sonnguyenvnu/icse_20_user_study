/** 
 * If in debug modus, print the names of the rules.
 * @param rulesets the RuleSets to print
 */
private static void printRuleNamesInDebug(RuleSets rulesets){
  if (LOG.isLoggable(Level.FINER)) {
    for (    Rule r : rulesets.getAllRules()) {
      LOG.finer("Loaded rule " + r.getName());
    }
  }
}
