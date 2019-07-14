/** 
 * Returns property type. Raw types are detected.
 */
public Class getType(){
  if (type == null) {
    if (fieldDescriptor != null) {
      type=fieldDescriptor.getRawType();
    }
 else     if (readMethodDescriptor != null) {
      type=getGetter(true).getGetterRawType();
    }
 else     if (writeMethodDescriptor != null) {
      type=getSetter(true).getSetterRawType();
    }
  }
  return type;
}
