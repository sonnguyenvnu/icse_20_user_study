/** 
 * Returns <code>true</code> if this property has only a setter method.
 */
public boolean isSetterOnly(){
  return (fieldDescriptor == null) && (readMethodDescriptor == null);
}
