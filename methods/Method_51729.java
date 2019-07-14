private void generateSidebar(Map<Language,List<RuleSet>> sortedRulesets) throws IOException {
  SidebarGenerator generator=new SidebarGenerator(writer,root);
  generator.generateSidebar(sortedRulesets);
}
