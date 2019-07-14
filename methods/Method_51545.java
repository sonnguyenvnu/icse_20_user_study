private static boolean hasAttributeSetTrue(Element element,String attributeId){
  return element.hasAttribute(attributeId) && "true".equalsIgnoreCase(element.getAttribute(attributeId));
}
