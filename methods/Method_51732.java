/** 
 * Rulesets could potentially contain rules from various languages. But for built-in rulesets, all rules within one ruleset belong to one language. So we take the language of the first rule.
 * @param ruleset
 * @return the terse name of the ruleset's language
 */
private static Language getRuleSetLanguage(RuleSet ruleset){
  Collection<Rule> rules=ruleset.getRules();
  if (rules.isEmpty()) {
    throw new RuntimeException("Ruleset " + ruleset.getFileName() + " is empty!");
  }
  return rules.iterator().next().getLanguage();
}
