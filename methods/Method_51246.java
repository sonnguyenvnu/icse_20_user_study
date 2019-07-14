protected String getVariableValue(String name){
  if ("variableName".equals(name)) {
    return variableName;
  }
 else   if ("methodName".equals(name)) {
    return methodName;
  }
 else   if ("className".equals(name)) {
    return className;
  }
 else   if ("packageName".equals(name)) {
    return packageName;
  }
 else {
    final PropertyDescriptor<?> propertyDescriptor=rule.getPropertyDescriptor(name);
    return String.valueOf(rule.getProperty(propertyDescriptor));
  }
}
