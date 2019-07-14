/** 
 * Gets a mapping of property name to its value from the given property element.
 * @param propertyElement Property element
 * @return An entry of property name to its value
 */
private Entry<String,String> getPropertyValue(Element propertyElement){
  String name=propertyElement.getAttribute(PropertyDescriptorField.NAME.attributeName());
  return new SimpleEntry<>(name,valueFrom(propertyElement));
}
