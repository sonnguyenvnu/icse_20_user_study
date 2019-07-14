/** 
 * Parses a properties element looking only for the values of the properties defined or overridden.
 * @param propertiesNode Node to parse
 * @return A map of property names to their value
 */
private Map<String,String> getPropertyValuesFrom(Element propertiesNode){
  Map<String,String> overridenProperties=new HashMap<>();
  for (int i=0; i < propertiesNode.getChildNodes().getLength(); i++) {
    Node node=propertiesNode.getChildNodes().item(i);
    if (node.getNodeType() == Node.ELEMENT_NODE && PROPERTY.equals(node.getNodeName())) {
      Entry<String,String> overridden=getPropertyValue((Element)node);
      overridenProperties.put(overridden.getKey(),overridden.getValue());
    }
  }
  return overridenProperties;
}
