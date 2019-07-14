/** 
 * Creates a copied list of the property descriptors and returns it.
 * @return a copy of the property descriptors.
 * @deprecated Just use {@link #getPropertyDescriptors()}
 */
@Deprecated protected List<PropertyDescriptor<?>> copyPropertyDescriptors(){
  return new ArrayList<>(propertyDescriptors);
}
