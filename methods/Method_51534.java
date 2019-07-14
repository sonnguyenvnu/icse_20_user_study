/** 
 * Decorates a referenced rule with the metadata that are overridden in the given rule element. <p>Declaring a property in the overriding element throws an exception (the property must exist in the referenced rule).
 * @param referencedRule Referenced rule
 * @param ruleSetReference the ruleset, where the referenced rule is defined
 * @param ruleElement    Element overriding some metadata about the rule
 * @return A rule reference to the referenced rule
 */
public RuleReference decorateRule(Rule referencedRule,RuleSetReference ruleSetReference,Element ruleElement){
  RuleReference ruleReference=new RuleReference(referencedRule,ruleSetReference);
  if (ruleElement.hasAttribute(DEPRECATED)) {
    ruleReference.setDeprecated(Boolean.parseBoolean(ruleElement.getAttribute(DEPRECATED)));
  }
  if (ruleElement.hasAttribute(NAME)) {
    ruleReference.setName(ruleElement.getAttribute(NAME));
  }
  if (ruleElement.hasAttribute(MESSAGE)) {
    ruleReference.setMessage(ruleElement.getAttribute(MESSAGE));
  }
  if (ruleElement.hasAttribute(EXTERNAL_INFO_URL)) {
    ruleReference.setExternalInfoUrl(ruleElement.getAttribute(EXTERNAL_INFO_URL));
  }
  for (int i=0; i < ruleElement.getChildNodes().getLength(); i++) {
    Node node=ruleElement.getChildNodes().item(i);
    if (node.getNodeType() == Node.ELEMENT_NODE) {
switch (node.getNodeName()) {
case DESCRIPTION:
        ruleReference.setDescription(parseTextNode(node));
      break;
case EXAMPLE:
    ruleReference.addExample(parseTextNode(node));
  break;
case PRIORITY:
ruleReference.setPriority(RulePriority.valueOf(Integer.parseInt(parseTextNode(node))));
break;
case PROPERTIES:
setPropertyValues(ruleReference,(Element)node);
break;
default :
throw new IllegalArgumentException("Unexpected element <" + node.getNodeName() + "> encountered as child of <rule> element for Rule " + ruleReference.getName());
}
}
}
return ruleReference;
}
