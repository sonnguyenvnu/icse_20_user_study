@Override public boolean isPropertyOverridden(PropertyDescriptor<?> descriptor){
  return propertyValues != null && propertyValues.containsKey(descriptor);
}
