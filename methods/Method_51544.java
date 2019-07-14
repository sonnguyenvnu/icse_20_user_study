/** 
 * Gets the string value from a property node. 
 */
private static String valueFrom(Element propertyNode){
  String strValue=propertyNode.getAttribute(DEFAULT_VALUE.attributeName());
  if (StringUtils.isNotBlank(strValue)) {
    return strValue;
  }
  final NodeList nodeList=propertyNode.getChildNodes();
  for (int i=0; i < nodeList.getLength(); i++) {
    Node node=nodeList.item(i);
    if (node.getNodeType() == Node.ELEMENT_NODE && "value".equals(node.getNodeName())) {
      return parseTextNode(node);
    }
  }
  return null;
}
