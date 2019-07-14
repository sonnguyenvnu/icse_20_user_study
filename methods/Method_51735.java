private String determineDefaultValueAsString(PropertyDescriptor<?> propertyDescriptor,Rule rule,boolean pad){
  String defaultValue="";
  Object realDefaultValue=rule.getProperty(propertyDescriptor);
  @SuppressWarnings("unchecked") PropertyDescriptor<Object> captured=(PropertyDescriptor<Object>)propertyDescriptor;
  if (realDefaultValue != null) {
    defaultValue=captured.asDelimitedString(realDefaultValue);
    if (pad && propertyDescriptor.isMultiValue()) {
      @SuppressWarnings("unchecked") MultiValuePropertyDescriptor<List<?>> multiPropertyDescriptor=(MultiValuePropertyDescriptor<List<?>>)propertyDescriptor;
      defaultValue=defaultValue.replaceAll(Pattern.quote(String.valueOf(multiPropertyDescriptor.multiValueDelimiter()))," " + multiPropertyDescriptor.multiValueDelimiter() + " ");
    }
  }
  return defaultValue;
}
