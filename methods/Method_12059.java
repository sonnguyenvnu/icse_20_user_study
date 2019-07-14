/** 
 * Returns entries in the order how they should be applied, i.e. inner-to-outer.
 */
private List<RuleEntry> getSortedEntries(){
  List<RuleEntry> ruleEntries=new ArrayList<RuleEntry>(methodRules.size() + testRules.size());
  for (  MethodRule rule : methodRules) {
    ruleEntries.add(new RuleEntry(rule,RuleEntry.TYPE_METHOD_RULE,orderValues.get(rule)));
  }
  for (  TestRule rule : testRules) {
    ruleEntries.add(new RuleEntry(rule,RuleEntry.TYPE_TEST_RULE,orderValues.get(rule)));
  }
  Collections.sort(ruleEntries,ENTRY_COMPARATOR);
  return ruleEntries;
}
