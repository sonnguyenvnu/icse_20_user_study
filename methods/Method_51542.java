/** 
 * Finds out if the property element defines a property.
 * @param node Property element
 * @return True if this element defines a new property, false if this is just stating a value
 */
private static boolean isPropertyDefinition(Element node){
  return node.hasAttribute(PropertyDescriptorField.TYPE.attributeName());
}
