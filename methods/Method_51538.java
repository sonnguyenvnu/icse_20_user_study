/** 
 * Parses the properties node and adds property definitions to the builder. Doesn't care for value overriding, that will be handled after the rule instantiation.
 * @param builder        Rule builder
 * @param propertiesNode Node to parse
 */
private void parsePropertiesForDefinitions(RuleBuilder builder,Node propertiesNode){
  for (int i=0; i < propertiesNode.getChildNodes().getLength(); i++) {
    Node node=propertiesNode.getChildNodes().item(i);
    if (node.getNodeType() == Node.ELEMENT_NODE && PROPERTY.equals(node.getNodeName()) && isPropertyDefinition((Element)node)) {
      PropertyDescriptor<?> descriptor=parsePropertyDefinition((Element)node);
      builder.defineProperty(descriptor);
    }
  }
}
