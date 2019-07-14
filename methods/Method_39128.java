/** 
 * Lookups for annotated properties. Caches all annotated properties on the first action class scan. 
 */
protected PropertyDescriptor[] lookupAnnotatedProperties(final Class type){
  PropertyDescriptor[] properties=annotatedProperties.get(type);
  if (properties != null) {
    return properties;
  }
  ClassDescriptor cd=ClassIntrospector.get().lookup(type);
  PropertyDescriptor[] allProperties=cd.getAllPropertyDescriptors();
  List<PropertyDescriptor> list=new ArrayList<>();
  for (  PropertyDescriptor propertyDescriptor : allProperties) {
    Annotation ann=null;
    if (propertyDescriptor.getFieldDescriptor() != null) {
      ann=propertyDescriptor.getFieldDescriptor().getField().getAnnotation(annotations);
    }
    if (ann == null && propertyDescriptor.getWriteMethodDescriptor() != null) {
      ann=propertyDescriptor.getWriteMethodDescriptor().getMethod().getAnnotation(annotations);
    }
    if (ann == null && propertyDescriptor.getReadMethodDescriptor() != null) {
      ann=propertyDescriptor.getReadMethodDescriptor().getMethod().getAnnotation(annotations);
    }
    if (ann != null) {
      list.add(propertyDescriptor);
    }
  }
  if (list.isEmpty()) {
    properties=EMPTY;
  }
 else {
    properties=list.toArray(new PropertyDescriptor[0]);
  }
  annotatedProperties.put(type,properties);
  return properties;
}
