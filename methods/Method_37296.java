/** 
 * Locates property field. Field is being searched also in all superclasses of current class.
 */
protected FieldDescriptor findField(final String fieldName){
  FieldDescriptor fieldDescriptor=classDescriptor.getFieldDescriptor(fieldName,true);
  if (fieldDescriptor != null) {
    return fieldDescriptor;
  }
  Class[] superclasses=classDescriptor.getAllSuperclasses();
  for (  Class superclass : superclasses) {
    ClassDescriptor classDescriptor=ClassIntrospector.get().lookup(superclass);
    fieldDescriptor=classDescriptor.getFieldDescriptor(fieldName,true);
    if (fieldDescriptor != null) {
      return fieldDescriptor;
    }
  }
  return null;
}
