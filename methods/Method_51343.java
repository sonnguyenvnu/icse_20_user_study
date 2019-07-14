@Override @Deprecated public boolean usesDefaultValues(){
  Map<PropertyDescriptor<?>,Object> valuesByProperty=getPropertiesByPropertyDescriptor();
  if (valuesByProperty.isEmpty()) {
    return true;
  }
  for (  Entry<PropertyDescriptor<?>,Object> entry : valuesByProperty.entrySet()) {
    if (!CollectionUtil.areEqual(entry.getKey().defaultValue(),entry.getValue())) {
      return false;
    }
  }
  return true;
}
