/** 
 * {@inheritDoc}
 */
@Override public ClassDescriptor lookup(final Class type){
  return cache.get(type,() -> new ClassDescriptor(type,scanAccessible,enhancedProperties,includeFieldsAsProperties,propertyFieldPrefix));
}
