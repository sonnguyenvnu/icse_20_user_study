/** 
 * Parses a rule element and returns a new rule instance. <p>Notes: The ruleset name is not set here. Exceptions raised from this method indicate invalid XML structure, with regards to the expected schema, while RuleBuilder validates the semantics.
 * @param ruleElement The rule element to parse
 * @return A new instance of the rule described by this element
 * @throws IllegalArgumentException if the element doesn't describe a valid rule.
 */
public Rule buildRule(Element ruleElement){
  checkRequiredAttributesArePresent(ruleElement);
  String name=ruleElement.getAttribute(NAME);
  RuleBuilder builder=new RuleBuilder(name,ruleElement.getAttribute(CLASS),ruleElement.getAttribute("language"));
  if (ruleElement.hasAttribute(MINIMUM_LANGUAGE_VERSION)) {
    builder.minimumLanguageVersion(ruleElement.getAttribute(MINIMUM_LANGUAGE_VERSION));
  }
  if (ruleElement.hasAttribute(MAXIMUM_LANGUAGE_VERSION)) {
    builder.maximumLanguageVersion(ruleElement.getAttribute(MAXIMUM_LANGUAGE_VERSION));
  }
  if (ruleElement.hasAttribute(SINCE)) {
    builder.since(ruleElement.getAttribute(SINCE));
  }
  builder.message(ruleElement.getAttribute(MESSAGE));
  builder.externalInfoUrl(ruleElement.getAttribute(EXTERNAL_INFO_URL));
  builder.setDeprecated(hasAttributeSetTrue(ruleElement,DEPRECATED));
  builder.usesDFA(hasAttributeSetTrue(ruleElement,"dfa"));
  builder.usesTyperesolution(hasAttributeSetTrue(ruleElement,"typeResolution"));
  Element propertiesElement=null;
  final NodeList nodeList=ruleElement.getChildNodes();
  for (int i=0; i < nodeList.getLength(); i++) {
    Node node=nodeList.item(i);
    if (node.getNodeType() != Node.ELEMENT_NODE) {
      continue;
    }
switch (node.getNodeName()) {
case DESCRIPTION:
      builder.description(parseTextNode(node));
    break;
case EXAMPLE:
  builder.addExample(parseTextNode(node));
break;
case PRIORITY:
builder.priority(Integer.parseInt(parseTextNode(node).trim()));
break;
case PROPERTIES:
parsePropertiesForDefinitions(builder,node);
propertiesElement=(Element)node;
break;
default :
throw new IllegalArgumentException("Unexpected element <" + node.getNodeName() + "> encountered as child of <rule> element for Rule " + name);
}
}
Rule rule;
try {
rule=builder.build();
}
 catch (ClassNotFoundException|IllegalAccessException|InstantiationException e) {
LOG.log(Level.SEVERE,"Error instantiating a rule",e);
throw new RuntimeException(e);
}
if (propertiesElement != null) {
setPropertyValues(rule,propertiesElement);
}
return rule;
}
