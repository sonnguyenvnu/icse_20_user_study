/** 
 * This method is responsible for execution of commands: <ul> <li> {@link AutomationCommands#REMOVE_MODULE_TYPES}<li> {@link AutomationCommands#REMOVE_TEMPLATES}<li> {@link AutomationCommands#REMOVE_RULES}<li> {@link AutomationCommands#REMOVE_RULE}</ul>
 */
@Override public String execute(){
  if (parsingResult != SUCCESS) {
    return parsingResult;
  }
switch (providerType) {
case AutomationCommands.MODULE_TYPE_PROVIDER:
    return autoCommands.remove(AutomationCommands.MODULE_TYPE_PROVIDER,url);
case AutomationCommands.TEMPLATE_PROVIDER:
  return autoCommands.remove(AutomationCommands.TEMPLATE_PROVIDER,url);
case AutomationCommands.RULE_PROVIDER:
if (command == AutomationCommands.REMOVE_RULE) {
  return autoCommands.removeRule(id);
}
 else if (command == AutomationCommands.REMOVE_RULES) {
  return autoCommands.removeRules(id);
}
}
return FAIL;
}
