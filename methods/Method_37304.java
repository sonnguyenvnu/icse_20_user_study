/** 
 * Resolves key type for given property descriptor.
 */
public Class resolveKeyType(final boolean declared){
  Class keyType=null;
  Getter getter=getGetter(declared);
  if (getter != null) {
    keyType=getter.getGetterRawKeyComponentType();
  }
  if (keyType == null) {
    FieldDescriptor fieldDescriptor=getFieldDescriptor();
    if (fieldDescriptor != null) {
      keyType=fieldDescriptor.getRawKeyComponentType();
    }
  }
  return keyType;
}
