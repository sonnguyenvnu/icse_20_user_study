private void logRulesUsed(RuleSets rules){
  project.log("Using these rulesets: " + configuration.getRuleSets(),Project.MSG_VERBOSE);
  RuleSet[] ruleSets=rules.getAllRuleSets();
  for (  RuleSet ruleSet : ruleSets) {
    for (    Rule rule : ruleSet.getRules()) {
      project.log("Using rule " + rule.getName(),Project.MSG_VERBOSE);
    }
  }
}
