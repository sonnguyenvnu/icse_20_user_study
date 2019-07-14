/** 
 * Creates a copied map of the values of the properties and returns it.
 * @return a copy of the values
 * @deprecated Just use {@link #getPropertiesByPropertyDescriptor()} or {@link #getOverriddenPropertiesByPropertyDescriptor()}
 */
@Deprecated protected Map<PropertyDescriptor<?>,Object> copyPropertyValues(){
  return new HashMap<>(propertyValuesByDescriptor);
}
