/** 
 * This method is responsible for execution of commands: <ul> <li> {@link AutomationCommands#EXPORT_MODULE_TYPES}<li> {@link AutomationCommands#EXPORT_TEMPLATES}<li> {@link AutomationCommands#EXPORT_RULES}</ul>
 */
@SuppressWarnings("unchecked") @Override public String execute(){
  if (parsingResult != SUCCESS) {
    return parsingResult;
  }
  @SuppressWarnings("rawtypes") Set set=new HashSet();
switch (providerType) {
case AutomationCommands.MODULE_TYPE_PROVIDER:
    @SuppressWarnings("rawtypes") Collection collection=autoCommands.getTriggers(locale);
  if (collection != null) {
    set.addAll(collection);
  }
collection=autoCommands.getConditions(locale);
if (collection != null) {
set.addAll(collection);
}
collection=autoCommands.getActions(locale);
if (collection != null) {
set.addAll(collection);
}
try {
return autoCommands.exportModuleTypes(parserType,set,file);
}
 catch (Exception e) {
return getStackTrace(e);
}
case AutomationCommands.TEMPLATE_PROVIDER:
collection=autoCommands.getTemplates(locale);
if (collection != null) {
set.addAll(collection);
}
try {
return autoCommands.exportTemplates(parserType,set,file);
}
 catch (Exception e) {
return getStackTrace(e);
}
case AutomationCommands.RULE_PROVIDER:
collection=autoCommands.getRules();
if (collection != null) {
set.addAll(collection);
}
try {
return autoCommands.exportRules(parserType,set,file);
}
 catch (Exception e) {
return getStackTrace(e);
}
}
return String.format("%s : Unsupported provider type!",FAIL);
}
