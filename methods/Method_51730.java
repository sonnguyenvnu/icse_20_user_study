private Iterator<RuleSet> resolveAdditionalRulesets(List<String> additionalRulesets) throws RuleSetNotFoundException {
  if (additionalRulesets == null) {
    return Collections.emptyIterator();
  }
  List<RuleSet> rulesets=new ArrayList<>();
  RuleSetFactory ruleSetFactory=new RuleSetFactory();
  for (  String filename : additionalRulesets) {
    try {
      if (!filename.contains("pmd-test") && !filename.contains("pmd-core")) {
        rulesets.add(ruleSetFactory.createRuleSet(filename));
      }
 else {
        LOG.fine("Ignoring ruleset " + filename);
      }
    }
 catch (    IllegalArgumentException e) {
      LOG.log(Level.WARNING,"ruleset file " + filename + " ignored (" + e.getMessage() + ")",e);
    }
  }
  return rulesets.iterator();
}
