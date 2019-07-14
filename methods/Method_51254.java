@Override @Deprecated public boolean usesDefaultValues(){
  List<PropertyDescriptor<?>> descriptors=getOverriddenPropertyDescriptors();
  if (!descriptors.isEmpty()) {
    return false;
  }
  for (  PropertyDescriptor<?> desc : descriptors) {
    if (!Objects.equals(desc.defaultValue(),getProperty(desc))) {
      return false;
    }
  }
  return getRule().usesDefaultValues();
}
