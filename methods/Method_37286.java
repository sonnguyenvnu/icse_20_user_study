/** 
 * Resolves raw component type for given index. This value is NOT cached.
 */
public Class[] resolveRawComponentTypes(){
  return ClassUtil.getComponentTypes(type,classDescriptor.getType());
}
