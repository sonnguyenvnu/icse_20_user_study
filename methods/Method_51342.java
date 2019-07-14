@Override public final List<PropertyDescriptor<?>> getOverriddenPropertyDescriptors(){
  return new ArrayList<>(propertyValuesByDescriptor.keySet());
}
