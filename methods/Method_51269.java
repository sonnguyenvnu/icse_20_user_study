private BaseXPath createXPath(final String xpathQueryString,final Navigator navigator) throws JaxenException {
  final BaseXPath xpath=new BaseXPath(xpathQueryString,navigator);
  if (properties.size() > 1) {
    final SimpleVariableContext vc=new SimpleVariableContext();
    for (    Entry<PropertyDescriptor<?>,Object> e : properties.entrySet()) {
      final String propName=e.getKey().name();
      if (!"xpath".equals(propName)) {
        final Object value=e.getValue();
        vc.setVariableValue(propName,value != null ? value.toString() : null);
      }
    }
    xpath.setVariableContext(vc);
  }
  return xpath;
}
