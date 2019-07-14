public void generateSidebar(Map<Language,List<RuleSet>> sortedRulesets) throws IOException {
  Map<String,Object> sidebar=loadSidebar();
  Map<String,Object> ruleReference=extractRuleReference(sidebar);
  ruleReference.put("folderitems",generateRuleReferenceSection(sortedRulesets));
  writeSidebar(sidebar);
}
