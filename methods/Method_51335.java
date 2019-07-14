/** 
 * Adds this property's attributes to the map. Subclasses can override this to add more  {@link PropertyDescriptorField}.
 * @param attributes The map to fill
 */
protected void addAttributesTo(Map<PropertyDescriptorField,String> attributes){
  attributes.put(NAME,name);
  attributes.put(DESCRIPTION,description);
  attributes.put(DEFAULT_VALUE,defaultAsString());
}
