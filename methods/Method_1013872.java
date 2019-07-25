/** 
 * This method is responsible for execution of commands: <ul> <li> {@link AutomationCommands#IMPORT_MODULE_TYPES}<li> {@link AutomationCommands#IMPORT_TEMPLATES}<li> {@link AutomationCommands#IMPORT_RULES}</ul>
 */
@Override public String execute(){
  if (parsingResult != SUCCESS) {
    return parsingResult;
  }
  try {
switch (providerType) {
case AutomationCommands.MODULE_TYPE_PROVIDER:
      autoCommands.importModuleTypes(parserType,url);
    break;
case AutomationCommands.TEMPLATE_PROVIDER:
  autoCommands.importTemplates(parserType,url);
break;
case AutomationCommands.RULE_PROVIDER:
autoCommands.importRules(parserType,url);
break;
}
}
 catch (Exception e) {
return getStackTrace(e);
}
return SUCCESS + "\n";
}
