boolean requiresHashCode(AttributeInfo attributeInfo){
  if (attributeInfo instanceof ViewAttributeInfo) {
    return true;
  }
  return globalRequireHashCode || getConfigurationForPackage(attributeInfo.getPackageName()).getRequireHashCode();
}
