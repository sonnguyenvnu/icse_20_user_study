@Override public String execute(){
  if (parsingResult != SUCCESS) {
    return parsingResult;
  }
  if (hasEnable) {
    autoCommands.setEnabled(uid,enable);
    return SUCCESS;
  }
 else {
    RuleStatus status=autoCommands.getRuleStatus(uid);
    if (status != null) {
      return Printer.printRuleStatus(uid,status);
    }
  }
  return FAIL;
}
