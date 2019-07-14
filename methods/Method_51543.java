/** 
 * Parses a property definition node and returns the defined property descriptor.
 * @param propertyElement Property node to parse
 * @return The property descriptor
 */
private static PropertyDescriptor<?> parsePropertyDefinition(Element propertyElement){
  String typeId=propertyElement.getAttribute(PropertyDescriptorField.TYPE.attributeName());
  PropertyDescriptorExternalBuilder<?> pdFactory=PropertyTypeId.factoryFor(typeId);
  if (pdFactory == null) {
    throw new IllegalArgumentException("No property descriptor factory for type: " + typeId);
  }
  Map<PropertyDescriptorField,String> values=new HashMap<>();
  NamedNodeMap atts=propertyElement.getAttributes();
  for (int i=0; i < atts.getLength(); i++) {
    Attr a=(Attr)atts.item(i);
    values.put(PropertyDescriptorField.getConstant(a.getName()),a.getValue());
  }
  if (StringUtils.isBlank(values.get(DEFAULT_VALUE))) {
    NodeList children=propertyElement.getElementsByTagName(DEFAULT_VALUE.attributeName());
    if (children.getLength() == 1) {
      values.put(DEFAULT_VALUE,children.item(0).getTextContent());
    }
 else {
      throw new IllegalArgumentException("No value defined!");
    }
  }
  return pdFactory.build(values);
}
