public boolean hasPrefixesOrSuffixes(){
  for (  PropertyDescriptor<?> desc : getPropertyDescriptors()) {
    if (desc instanceof StringMultiProperty) {
      List<String> values=getProperty((StringMultiProperty)desc);
      if (!values.isEmpty()) {
        return true;
      }
    }
  }
  return false;
}
