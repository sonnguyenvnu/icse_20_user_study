/** 
 * Returns all bean property names.
 */
protected String[] getAllBeanPropertyNames(final Class type,final boolean declared){
  ClassDescriptor classDescriptor=ClassIntrospector.get().lookup(type);
  PropertyDescriptor[] propertyDescriptors=classDescriptor.getAllPropertyDescriptors();
  ArrayList<String> names=new ArrayList<>(propertyDescriptors.length);
  for (  PropertyDescriptor propertyDescriptor : propertyDescriptors) {
    MethodDescriptor getter=propertyDescriptor.getReadMethodDescriptor();
    if (getter != null) {
      if (getter.matchDeclared(declared)) {
        names.add(propertyDescriptor.getName());
      }
    }
 else     if (includeFields) {
      FieldDescriptor field=propertyDescriptor.getFieldDescriptor();
      if (field != null) {
        if (field.matchDeclared(declared)) {
          names.add(field.getName());
        }
      }
    }
  }
  return names.toArray(new String[0]);
}
