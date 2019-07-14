private Element createRuleSetElement(RuleSet ruleSet){
  Element ruleSetElement=document.createElementNS(RULESET_2_0_0_NS_URI,"ruleset");
  ruleSetElement.setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
  ruleSetElement.setAttributeNS("http://www.w3.org/2001/XMLSchema-instance","xsi:schemaLocation",RULESET_2_0_0_NS_URI + " https://pmd.sourceforge.io/ruleset_2_0_0.xsd");
  ruleSetElement.setAttribute("name",ruleSet.getName());
  Element descriptionElement=createDescriptionElement(ruleSet.getDescription());
  ruleSetElement.appendChild(descriptionElement);
  for (  String excludePattern : ruleSet.getExcludePatterns()) {
    Element excludePatternElement=createExcludePatternElement(excludePattern);
    ruleSetElement.appendChild(excludePatternElement);
  }
  for (  String includePattern : ruleSet.getIncludePatterns()) {
    Element includePatternElement=createIncludePatternElement(includePattern);
    ruleSetElement.appendChild(includePatternElement);
  }
  for (  Rule rule : ruleSet.getRules()) {
    Element ruleElement=createRuleElement(rule);
    if (ruleElement != null) {
      ruleSetElement.appendChild(ruleElement);
    }
  }
  return ruleSetElement;
}
