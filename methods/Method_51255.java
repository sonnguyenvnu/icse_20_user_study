@Override @Deprecated public void useDefaultValueFor(PropertyDescriptor<?> desc){
  getRule().useDefaultValueFor(desc);
  if (propertyValues == null) {
    return;
  }
  propertyValues.remove(desc);
  if (propertyDescriptors != null) {
    propertyDescriptors.remove(desc);
  }
}
