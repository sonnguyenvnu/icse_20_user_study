/** 
 * Resolves component type for given property descriptor.
 */
public Class resolveComponentType(final boolean declared){
  Class componentType=null;
  Getter getter=getGetter(declared);
  if (getter != null) {
    componentType=getter.getGetterRawComponentType();
  }
  if (componentType == null) {
    FieldDescriptor fieldDescriptor=getFieldDescriptor();
    if (fieldDescriptor != null) {
      componentType=fieldDescriptor.getRawComponentType();
    }
  }
  return componentType;
}
