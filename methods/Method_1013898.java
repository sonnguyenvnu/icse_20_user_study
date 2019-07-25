@Override public void added(ModuleType moduleType){
  String moduleTypeName=moduleType.getUID();
  for (  ModuleHandlerFactory moduleHandlerFactory : allModuleHandlerFactories) {
    Collection<String> moduleTypes=moduleHandlerFactory.getTypes();
    if (moduleTypes.contains(moduleTypeName)) {
synchronized (this) {
        this.moduleHandlerFactories.put(moduleTypeName,moduleHandlerFactory);
      }
      break;
    }
  }
  Set<String> rules=null;
synchronized (this) {
    Set<String> rulesPerModule=mapModuleTypeToRules.get(moduleTypeName);
    if (rulesPerModule != null) {
      rules=new HashSet<String>();
      rules.addAll(rulesPerModule);
    }
  }
  if (rules != null) {
    for (    String rUID : rules) {
      RuleStatus ruleStatus=getRuleStatus(rUID);
      if (ruleStatus == RuleStatus.UNINITIALIZED) {
        scheduleRuleInitialization(rUID);
      }
    }
  }
}
