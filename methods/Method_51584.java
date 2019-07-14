private Element createSingleRuleElement(Language language,LanguageVersion minimumLanguageVersion,LanguageVersion maximumLanguageVersion,Boolean deprecated,String name,String since,String ref,String message,String externalInfoUrl,String clazz,Boolean dfa,Boolean typeResolution,Boolean multifile,String description,RulePriority priority,List<PropertyDescriptor<?>> propertyDescriptors,Map<PropertyDescriptor<?>,Object> propertiesByPropertyDescriptor,List<String> examples){
  Element ruleElement=createRuleElement();
  if (language != null) {
    ruleElement.setAttribute("language",language.getTerseName());
  }
  if (minimumLanguageVersion != null) {
    ruleElement.setAttribute("minimumLanguageVersion",minimumLanguageVersion.getVersion());
  }
  if (maximumLanguageVersion != null) {
    ruleElement.setAttribute("maximumLanguageVersion",maximumLanguageVersion.getVersion());
  }
  setIfNonNull(deprecated,ruleElement,"deprecated");
  setIfNonNull(name,ruleElement,"name");
  setIfNonNull(since,ruleElement,"since");
  setIfNonNull(ref,ruleElement,"ref");
  setIfNonNull(message,ruleElement,"message");
  setIfNonNull(clazz,ruleElement,"class");
  setIfNonNull(externalInfoUrl,ruleElement,"externalInfoUrl");
  setIfNonNull(dfa,ruleElement,"dfa");
  setIfNonNull(typeResolution,ruleElement,"typeResolution");
  if (description != null) {
    Element descriptionElement=createDescriptionElement(description);
    ruleElement.appendChild(descriptionElement);
  }
  if (priority != null) {
    Element priorityElement=createPriorityElement(priority);
    ruleElement.appendChild(priorityElement);
  }
  Element propertiesElement=createPropertiesElement(propertyDescriptors,propertiesByPropertyDescriptor);
  if (propertiesElement != null) {
    ruleElement.appendChild(propertiesElement);
  }
  if (examples != null) {
    for (    String example : examples) {
      Element exampleElement=createExampleElement(example);
      ruleElement.appendChild(exampleElement);
    }
  }
  return ruleElement;
}
