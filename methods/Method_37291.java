/** 
 * Resolves raw return component types This value is NOT cached.
 */
public Class[] resolveRawReturnComponentTypes(){
  return ClassUtil.getComponentTypes(returnType,classDescriptor.getType());
}
