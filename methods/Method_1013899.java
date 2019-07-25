@Override public void updated(ModuleType oldElement,ModuleType moduleType){
  if (moduleType.equals(oldElement)) {
    return;
  }
  String moduleTypeName=moduleType.getUID();
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
      final RuleStatus ruleStatus=getRuleStatus(rUID);
      if (ruleStatus == null) {
        continue;
      }
      if (ruleStatus.equals(RuleStatus.IDLE) || ruleStatus.equals(RuleStatus.RUNNING)) {
        unregister(getManagedRule(rUID),RuleStatusDetail.HANDLER_MISSING_ERROR,"Update Module Type " + moduleType.getUID());
        setStatus(rUID,new RuleStatusInfo(RuleStatus.INITIALIZING));
      }
    }
  }
}
