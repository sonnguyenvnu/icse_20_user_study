/** 
 * This method is responsible for execution of commands: <ul> <li> {@link AutomationCommands#LIST_MODULE_TYPES}<li> {@link AutomationCommands#LIST_TEMPLATES}<li> {@link AutomationCommands#LIST_RULES}</ul>
 */
@Override public String execute(){
  if (parsingResult != SUCCESS) {
    return parsingResult;
  }
  if (providerType == AutomationCommands.MODULE_TYPE_PROVIDER) {
    return listModuleTypes();
  }
  if (providerType == AutomationCommands.TEMPLATE_PROVIDER) {
    return listTemplates();
  }
  if (providerType == AutomationCommands.RULE_PROVIDER) {
    return listRules();
  }
  return FAIL;
}
