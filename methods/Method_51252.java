/** 
 * @deprecated Use {@link #isPropertyOverridden(PropertyDescriptor)} instead
 */
@Deprecated public boolean hasOverriddenProperty(PropertyDescriptor<?> descriptor){
  return isPropertyOverridden(descriptor);
}
