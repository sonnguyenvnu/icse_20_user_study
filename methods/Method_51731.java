private Map<Language,List<RuleSet>> sortRulesets(Iterator<RuleSet> rulesets) throws RuleSetNotFoundException {
  SortedMap<Language,List<RuleSet>> rulesetsByLanguage=new TreeMap<>();
  while (rulesets.hasNext()) {
    RuleSet ruleset=rulesets.next();
    Language language=getRuleSetLanguage(ruleset);
    if (!rulesetsByLanguage.containsKey(language)) {
      rulesetsByLanguage.put(language,new ArrayList<RuleSet>());
    }
    rulesetsByLanguage.get(language).add(ruleset);
  }
  for (  List<RuleSet> rulesetsOfOneLanguage : rulesetsByLanguage.values()) {
    Collections.sort(rulesetsOfOneLanguage,new Comparator<RuleSet>(){
      @Override public int compare(      RuleSet o1,      RuleSet o2){
        return o1.getName().compareToIgnoreCase(o2.getName());
      }
    }
);
  }
  return rulesetsByLanguage;
}
