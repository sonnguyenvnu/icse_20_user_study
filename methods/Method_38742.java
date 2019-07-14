/** 
 * Returns <code>true</code> if resource is of provided resource type.
 */
public boolean isSameTypeAsResource(final Class type){
  return ClassUtil.isTypeOf(type,resource.getClass());
}
