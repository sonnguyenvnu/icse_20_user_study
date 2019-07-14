private Element createPropertyValueElement(PropertyDescriptor propertyDescriptor,Object value){
  Element propertyElement=document.createElementNS(RULESET_2_0_0_NS_URI,"property");
  propertyElement.setAttribute("name",propertyDescriptor.name());
  String valueString=propertyDescriptor.asDelimitedString(value);
  if (XPathRule.XPATH_DESCRIPTOR.equals(propertyDescriptor)) {
    Element valueElement=createCDATASectionElement("value",valueString);
    propertyElement.appendChild(valueElement);
  }
 else {
    propertyElement.setAttribute("value",valueString);
  }
  return propertyElement;
}
