/** 
 * Extracts generic component type of a property. Returns <code>Object.class</code> when property does not have component.
 */
protected Class extractGenericComponentType(final Getter getter){
  Class componentType=null;
  if (getter != null) {
    componentType=getter.getGetterRawComponentType();
  }
  if (componentType == null) {
    componentType=Object.class;
  }
  return componentType;
}
