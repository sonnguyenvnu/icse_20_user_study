List<Map<String,Object>> generateRuleReferenceSection(Map<Language,List<RuleSet>> sortedRulesets){
  List<Map<String,Object>> newFolderItems=new ArrayList<>();
  for (  Map.Entry<Language,List<RuleSet>> entry : sortedRulesets.entrySet()) {
    Map<String,Object> newFolderItem=new LinkedHashMap<>();
    newFolderItem.put("title",null);
    newFolderItem.put("output","web, pdf");
    Map<String,Object> subfolder=new LinkedHashMap<>();
    newFolderItem.put("subfolders",Arrays.asList(subfolder));
    subfolder.put("title",entry.getKey().getName() + " Rules");
    subfolder.put("output","web, pdf");
    List<Map<String,Object>> subfolderitems=new ArrayList<>();
    subfolder.put("subfolderitems",subfolderitems);
    Map<String,Object> ruleIndexSubfolderItem=new LinkedHashMap<>();
    ruleIndexSubfolderItem.put("title","Index");
    ruleIndexSubfolderItem.put("output","web, pdf");
    ruleIndexSubfolderItem.put("url","/pmd_rules_" + entry.getKey().getTerseName() + ".html");
    subfolderitems.add(ruleIndexSubfolderItem);
    for (    RuleSet ruleset : entry.getValue()) {
      Map<String,Object> subfolderitem=new LinkedHashMap<>();
      subfolderitem.put("title",ruleset.getName());
      subfolderitem.put("output","web, pdf");
      subfolderitem.put("url","/pmd_rules_" + entry.getKey().getTerseName() + "_" + RuleSetUtils.getRuleSetFilename(ruleset) + ".html");
      subfolderitems.add(subfolderitem);
    }
    newFolderItems.add(newFolderItem);
  }
  return newFolderItems;
}
