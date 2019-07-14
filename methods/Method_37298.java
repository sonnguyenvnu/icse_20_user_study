/** 
 * Returns <code>true</code> if this property has only a getter method.
 */
public boolean isGetterOnly(){
  return (fieldDescriptor == null) && (writeMethodDescriptor == null);
}
