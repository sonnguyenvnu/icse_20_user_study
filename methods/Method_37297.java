/** 
 * Returns <code>true</code> if this is an extended property with only field definition and without getter and setter.
 */
public boolean isFieldOnly(){
  return (readMethodDescriptor == null) && (writeMethodDescriptor == null);
}
