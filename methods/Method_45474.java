/** 
 * Sets dynamic attribute.
 * @param dynamicAttrKey   the dynamic attribute key
 * @param dynamicAttrValue the dynamic attribute value
 * @return the dynamic attribute
 */
public ProviderInfo setDynamicAttr(String dynamicAttrKey,Object dynamicAttrValue){
  if (dynamicAttrValue == null) {
    dynamicAttrs.remove(dynamicAttrKey);
  }
 else {
    dynamicAttrs.put(dynamicAttrKey,dynamicAttrValue);
  }
  return this;
}
